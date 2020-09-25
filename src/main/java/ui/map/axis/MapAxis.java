package ui.map.axis;


import io.reactivex.subjects.PublishSubject;
import util.Debouncer;
import util.Util;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class MapAxis {

    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private Double[][] data;

    private PublishSubject<Double[][]> publishSubject;
    private Debouncer debouncer;

    private int rollOverRowIndex = -1;
    private int rollOverColumnIndex = -1;

    private double maxValue;

    private MapAxis(Double[][] data) {
        this.table = this.createAxis(data);
        // Handle Copy/Paste
        MapAxisExcelAdapter excelAdapter = new MapAxisExcelAdapter(this);
        this.data = data;
        this.debouncer = new Debouncer();

        this.scrollPane = new JScrollPane(table);
        this.publishSubject = PublishSubject.create();
    }

    public PublishSubject<Double[][]> getPublishSubject() {
        return publishSubject;
    }

    public void setTableData(Double[][] data) {
        this.data = data;
        setMaxValue(data);
        tableModel.setDataVector(this.data, new Double[data[0].length]);
        enforceTableColumnWidth(this.table);
    }

    private JTable createAxis(final Double[][] data) {
        setMaxValue(data);

        tableModel = new DefaultTableModel(data, data[0]) {
            @Override
            public Class<?> getColumnClass(int column) {
                return Double.class;
            }
        };


        final JTable table = new JTable(tableModel);

        RollOverListener rollOverListener = new RollOverListener();
        table.addMouseListener(rollOverListener);
        table.addMouseMotionListener(rollOverListener);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setGridColor(Color.BLACK);
        table.setRowSelectionAllowed(false);

        table.setTableHeader(null);

        enforceTableColumnWidth(table);

        table.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                Double[][] values = new Double[MapAxis.this.data.length][];

                for (int i = 0; i < MapAxis.this.data.length; i++) {
                    values[i] = new Double[MapAxis.this.data[i].length];
                    for (int j = 0; j < MapAxis.this.data[i].length; j++) {
                        values[i][j] = (Double) table.getModel().getValueAt(i, j);
                    }
                }

                MapAxis.this.data = values;

                debouncer.debounce(this, new Runnable() {
                    @Override
                    public void run() {
                        publishSubject.onNext(values);
                    }
                }, 100, TimeUnit.MILLISECONDS);
            }
        });

        return table;
    }

    public JTable getTable() {
        return table;
    }

    public Double[][] getData() {
        return data;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public static MapAxis getMapAxis(final Double[][] data) {
        return new MapAxis(data);
    }

    private void enforceTableColumnWidth(JTable table) {
        TableColumn column;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(55);
            column.setMaxWidth(55);

            DefaultTableCellRenderer centerRenderer = new DecimalFormatRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            column.setCellRenderer(centerRenderer);
        }
    }

    private class RollOverListener extends MouseInputAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rollOverRowIndex = -1;
            rollOverColumnIndex = -1;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            rollOverRowIndex = table.rowAtPoint(e.getPoint());
            rollOverColumnIndex = table.columnAtPoint(e.getPoint());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            rollOverRowIndex = table.rowAtPoint(e.getPoint());
            rollOverColumnIndex = table.columnAtPoint(e.getPoint());
        }
    }

    private class DecimalFormatRenderer extends DefaultTableCellRenderer {

        private final DecimalFormat formatter = new DecimalFormat("#.00");

        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            if (value != null) {
                value = formatter.format(value);
            } else {
                value = 0.0;
            }

            boolean hasSelection = table.getSelectedColumns().length > 0;

            if (hasSelection) {
                if (column >= table.getSelectedColumns()[0] && column <= table.getSelectedColumns()[table.getSelectedColumns().length - 1] && row >= table.getSelectedRows()[0] && row <= table.getSelectedRows()[table.getSelectedRows().length - 1]) {
                    setBackground(Util.newColorWithAlpha(Color.CYAN, 50));
                } else {
                    if (value instanceof String) {
                        double v = Double.parseDouble((String) value);
                        double norm;
                        if (maxValue != 0) {
                            norm = 1 - (v / maxValue);
                            setBackground(getColor(norm));
                        } else {
                            setBackground(null);
                        }
                    } else {
                        setBackground(null);
                    }
                }
            } else {
                if (value instanceof String) {
                    double v = Double.parseDouble((String) value);
                    double norm;
                    if (maxValue != 0) {
                        norm = 1 - (v / maxValue);
                        setBackground(getColor(norm));
                    } else {
                        setBackground(null);
                    }
                } else {
                    setBackground(null);
                }
            }

            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }

        private Color getColor(double value) {
            double H = value * 0.4; // Hue (note 0.4 = Green, see huge chart below)
            double S = 0.9; // Saturation
            double B = 0.9; // Brightness

            return Color.getHSBColor((float) H, (float) S, (float) B);
        }
    }

    private void setMaxValue(Double[][] data) {
        if (data != null) {
            double max = Double.MIN_VALUE;
            for (Double[] doubles : data) {
                for (Double value : doubles) {
                    if (value != null) {
                        max = Math.max(max, value);
                    }
                }
            }

            this.maxValue = max;
        } else {
            this.maxValue = 0;
        }
    }
}
