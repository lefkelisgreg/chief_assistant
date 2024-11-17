package gr.hua.dit.oop2;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Παρακαλώ δώστε τα σωστά ορίσματα.");
            return;
        }

        // Έλεγχος λειτουργιών
        if (args[0].equals("-list")) {
            // Λειτουργία 2: Δημιουργία λίστας αγορών
            if (args.length < 2) {
                System.out.println("Παρακαλώ δώστε τουλάχιστον ένα αρχείο συνταγής.");
                return;}
        }
    }
}
