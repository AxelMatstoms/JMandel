package se.matstoms.jmandel;

public class Complex {
    private double r;
    private double i;

    public Complex(double r, double i) {
        this.r = r;
        this.i = i;
    }

    public double real() {
        return this.r;
    }

    public double imag() {
        return this.i;
    }

    public Complex mult(Complex c) {
        double ar = this.r;
        double ai = this.i;
        double br = c.real();
        double bi = c.imag();
        double rr = ar * br - ai * bi;
        double ri = ar * bi + br * ai;
        return new Complex(rr, ri);
    }
    
    public Complex mult(double x) {
        return new Complex(this.r * x, this.i * x);
    }

    public Complex add(Complex c) {
        double ar = this.r;
        double ai = this.i;
        return new Complex(ar + c.real(), ai + c.imag());
    }

    public Complex square() {
        return this.mult(this);
    }

    public double abs() {
        return Math.sqrt(this.abs2());
    }

    public double abs2() {
        return r * r + i * i;
    }

}