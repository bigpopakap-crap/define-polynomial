
package definePolynomial;

import java.util.Iterator;

public class Polynomial implements Iterable<Double> {
    private final double[] coefs;

    public Polynomial(double... coefs) {
        this.coefs = new double[coefs.length];
        for (int i=0; i<coefs.length; i++) {
            this.coefs[i] = coefs[i];
        }
    }
    public Polynomial(Polynomial toCopy) {
        coefs = toCopy.toArray();
    }
    @Override
    public boolean equals(Object o) {
        if (!o.getClass().equals(getClass())) {
            return false;
        }
        Polynomial p = (Polynomial) o;
        if (p.getOrder() != getOrder()) {
            return false;
        }
        for (int i=0; i<=getOrder(); i++) {
            if (getCoef(i) != p.getCoef(i)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.coefs != null ? this.coefs.hashCode() : 0);
        return hash;
    }
    @Override
    public String toString() {
        String out = "";
        for (int i=0; i<=getOrder(); i++) {
            out += "(" + getCoef(i) + ")" + (getOrder() - i > 0 ? "x" : "") + (getOrder() - i > 1 ? "^" + (getOrder() - i) : "") + " + ";
        }
        return out.substring(0, out.length() - 3);
    }

    public double getCoef(int index) throws IndexOutOfBoundsException {
        try {
            return coefs[index];
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("attempted to get value at index " + index + " from polynomial of order " + getOrder());
        }
    }

    public double[] toArray() {
        double[] out = new double[getOrder() + 1];
        for (int i=0; i<=getOrder(); i++) {
            out[i] = coefs[i];
        }
        return out;
    }

    public int getOrder() {
        return coefs.length - 1;
    }

    public Iterator<Double> iterator() {
        return new Iterator() {
            private int index = 0;

            public boolean hasNext() {
                return index < getOrder() + 1;
            }

            public Object next() {
                return getCoef(index++);
            }

            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        };
    }

    public Polynomial add(Polynomial p) {
        double[] first = toArray();
        double[] second = p.toArray();
        if (first.length > second.length) {
            int index = first.length - 1;
            for (int i=second.length - 1; i>=0; i--) {
                first[index] += second[i];
                index--;
            }
            return new Polynomial(first);
        } else {
            int index = second.length - 1;
            for (int i=first.length - 1; i>=0; i--) {
                second[index] += first[i];
                index--;
            }
            return new Polynomial(second);
        }
    }

    public Polynomial subtract(Polynomial p) {
        double[] first = toArray();
        double[] second = p.toArray();
        if (first.length > second.length) {
            int index = first.length - 1;
            for (int i=second.length - 1; i>=0; i--) {
                first[index] -= second[i];
                index--;
            }
            return new Polynomial(first);
        } else {
            int index = second.length - 1;
            for (int i=first.length - 1; i>=0; i--) {
                second[index] -= first[i];
                index--;
            }
            return new Polynomial(second);
        }
    }

    public double eval(double x) {
        double out = 0;
        for (int i=0; i<=getOrder(); i++) {
            out += getCoef(i) * Math.pow(x, getOrder() - i);
        }
        return out;
    }

    public double[] eval(double... x) {
        double[] out = new double[x.length];
        for (int i=0; i<x.length; i++) {
            out[i] = eval(x[i]);
        }
        return out;
    }

    public double getMax(double a, double b, double step) {
        double max = eval(a);
        while (a <= b) {
            a += step;
            if (eval(a) > max) {
                max = eval(a);
            }
        }
        return max;
    }

    public double getMin(double a, double b, double step) {
        double min = eval(a);
        while (a <= b) {
            a += step;
            if (eval(a) < min) {
                min = eval(a);
            }
        }
        return min;
    }
}
