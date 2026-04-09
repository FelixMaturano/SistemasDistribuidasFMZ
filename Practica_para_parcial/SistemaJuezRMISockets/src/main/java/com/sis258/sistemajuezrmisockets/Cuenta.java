package com.sis258.sistemajuezrmisockets;
import java.io.Serializable;
public class Cuenta implements Serializable{
    private String banco;
    private String nro;
    private double saldo;
    public Cuenta(String banco, String nro, double saldo) {
        this.banco = banco;
        this.nro = nro;
        this.saldo = saldo;
    }
    public String getBanco() {
        return banco;
    }
    public void setBanco(String banco) {
        this.banco = banco;
    }
    public String getNro() {
        return nro;
    }
    public void setNro(String nro) {
        this.nro = nro;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    // Devuelve una representacion en texto de la cuenta 
    @Override
    public String toString(){
        return banco + "-" + nro + " - "+ saldo;
    }
}
