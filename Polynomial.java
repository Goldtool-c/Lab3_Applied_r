package sample;

import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Polynomial {

    public String X;
    public String Value;
    public String FloatX;
    public String Result;
    public int n1=1;
    Integer A[]=new Integer[10];
    Polynomial()
    {
        X="0";
        Value="0";
        FloatX="0";
        Result="0";
    }
    public void setValue(String v)
    {
        Value=v;
    }
    private int len()
    {
        int i=0;
        while(A[i]!=null)
        {
            i++;
        }
        return i;
    }
    int n;

    private String gorner()
    {
        int s = -A[0]+Integer.parseInt(X);
        int i=1;
        while (i<n1)
        {
            s=s*(-A[i]+Integer.parseInt(X));
            i++;
        }
        return Integer.toString(s);
    }
    public Double ToFloat()
    {
        double AF[]=new double[10];
        double x=Integer.parseInt(X);
        int i=0;
        while(i<n1)
        {
            AF[i]=A[i];
            i++;
        }
        double s = AF[n1-1];
        for (i = 1; i <= n1; ++i)
        {
            s *= Integer.parseInt(X);
            s += A[n1-i];
        }
        return s;
    }
    public String CalcResult()
    {
        double s=ToFloat();
        int IntS= (int) s;
        int i=0;
        double e=1.0;
        while((s-IntS)<e)
        {
            i++;
            e=e/10;
        }
        String st=(1)+"*"+(10)+"^(-"+i+")";
        return st;
    }
    Polynomial(String x)
    {
        FloatX="Shit";
        X=x;
        Value="this";
        Result="Blyad";
    }

    public void Coff(String tempA)
    {
        char temp='!';
        String form="0";
        int i=0,a=0;
        tempA=tempA+" ";
        temp=tempA.charAt(i);
        while(temp!=' ')
        {
            if (temp=='/')
            {
                A[a]=Integer.parseInt(form);
                a++;
                n=a;
                n1++;
                form="0";
            } else
            {
                form=form+Character.toString(temp);
            }
            i++;
            temp=tempA.charAt(i);
        }
        i=0;
        A[a]=Integer.parseInt(form);
    }
    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    public String getValue() {
        return Value;
    }

    public void setValue() {
        Value = gorner();
    }

    public String getFloatX() {
        return FloatX;
    }

    public void setFloatX() {
        FloatX = Double.toString(ToFloat());
    }

    public String getResult() {
        return Result;
    }

    public void setResult() {
        Result = CalcResult();
    }
}
