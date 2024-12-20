package laboratornyeworks;

import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {

    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;


    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients){
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from; }


    public Double getTo() {
        return to; }


    public Double getStep() {
        return step;
    }



    @Override
    public int getRowCount() {
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int col) {
        double x = from + step * row; // Вычисляем значение X для текущей строки
        Double result = 0.0;

        if (col == 0) {
            return x; // Если запрашивается первый столбец, возвращаем X
        }
        else if (col == 1) {
            // Если запрашивается второй столбец, вычисляем P(X)

            // Схема Горнера для вычисления P(X)
            for (int i = 0; i < coefficients.length; i++) {
                result = result * x + coefficients[i];
            }

            return result; // Возвращаем значение многочлена
        }
        else {
            for (int i = 0; i < coefficients.length; i++) {
            result = result * x + coefficients[i];
        }
            // Проверка наличия дробной части
            if (result % 1 != 0) {
                return false; // Дробная часть не равна нулю
            }
            else {
                return true; // Дробная часть равна нулю! Ура! :)
            }
        }

    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            default:
                return "Точное значение?";
        }


    }
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return Double.class;
            case 1:
                return Double.class;
            default:
                return boolean.class;
            }
        }
}
