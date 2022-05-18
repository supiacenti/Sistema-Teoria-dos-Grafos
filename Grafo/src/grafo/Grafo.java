/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Grafo {

    public static void main(String[] args) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter (
                "Selecione apenas .txt",
                "txt"
        );
        String pat = "";
        chooser.setFileFilter(filter);
        int retorno = chooser.showOpenDialog(null);
        if(retorno == JFileChooser.APPROVE_OPTION){
            pat += chooser.getSelectedFile().getAbsolutePath();
        }
        Path cam = Paths.get(pat);
        byte[] texto = Files.readAllBytes(cam);
        String leit = new String(texto);
        String leitu = leit.replaceAll(" ", "-");
        String leitura = leitu.replaceAll("\r\n", "-");
        
        String[] val = leitura.split("-");
        String[] valores = leit.split("\r\n");

        System.out.println("Grafo presente no arquivo .txt:");
        for(String s : valores){
            System.out.println(s);
        }
//        for(String r : val){
//            System.out.println(r);
//        }
        
        
        int tam = (Integer.parseInt(valores[0]) + 1);
        
        System.out.println("");
        resultados(val, valores);
        
        AdjGraph graph = new AdjGraph(tam);
        ArrayList<String> total = new ArrayList<>();        
        ArrayList<String> par = new ArrayList<>();
        ArrayList<String> impar = new ArrayList<>();
        for(int i = 1; i < val.length; i++){
            if(i % 2 == 0){
                par.add(val[i]);
            } else {
                impar.add(val[i]);
            }
        }
        for(int i = 1; i < val.length; i++){
            total.add(val[i]);
        }
        
        int[] pa = new int[(val.length - 1) / 2];
        int[] im = new int[(val.length - 1) / 2];
        int l = 0;
        int m = 0;
        for(String p: par){
            pa[l] = Integer.parseInt(p);
            l++;
        }
        for(String i: impar){
            im[m] = Integer.parseInt(i);
            m++;
        }
        
//        par.forEach(s -> {
//            System.out.println(s);
//        });
//        impar.forEach(r -> {
//            System.out.println(r);
//        });
        
//        for(int s : pa){
//            System.out.println(s);
//        }
//        for(int r : im){
//            System.out.println(r);
//        }
        
        for(int i = 0; i < pa.length; i++){
            graph.addEdge(pa[i], im[i]);
        }
        
        System.out.println("");
        graph.printGraph();
        
        List<String> unique
            = total.stream().distinct().collect(
                Collectors.toList());
        System.out.println("\nVértices:");
        System.out.println(unique);
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        GrafoLargura<String> larg = new GrafoLargura<>();
        unique.forEach(r -> {
            larg.adicionarVertice(r);
        });
        
        String[] a = new String[(val.length - 1) / 2];
        String[] b = new String[(val.length - 1) / 2];
        int c = 0;
        int d = 0;
        for(String p: par){
            a[c] = p;
            c++;
        }
        for(String i: impar){
            b[d] = i;
            d++;
        }
//        for(String s : a){
//            System.out.println(s);
//        }
//        for(String r : b){
//            System.out.println(r);
//        }
        for(int i = 0;  i < a.length; i++){
            larg.adicionarAresta(1.0, a[i], b[i]);
        }
        
        Scanner s = new Scanner(System.in);
        System.out.println("");
        System.out.println("|| Digite o vértice inicial da Busca em Largura: ");
        int busca = s.nextInt();
        int index = busca -1;
        System.out.println("\nBusca em Largura:");
        larg.buscaEmLargura(index);
    }

    private static void resultados(String[] a, String[] b) {
        int tam = b.length;
        System.out.println("Quantidade de Vértices: " + a[0]);
        System.out.println("Quantidade de Arestas: " + (tam - 1));
        List c = new ArrayList();
        int i = 1;
        while(i < a.length){
            c.add(a[i]);
            i++;
        }
        Map<Object, Integer> countMap = new HashMap<>();
        
        for (Object item: c) {
            if (countMap.containsKey(item))
                countMap.put(item, countMap.get(item) + 1);
            else
                countMap.put(item, 1);
        }
        System.out.println("Grau de cada Vértice: " + countMap);
        
        int menor = Integer.MAX_VALUE;
        int maior = Integer.MIN_VALUE;
        
        for(int m : countMap.values()){
            if (m < menor){
                menor = m;
            }
        }
        for(int m : countMap.values()){
            if (m > maior){
                maior = m;
            }
        }
        System.out.println("Menor grau: " + menor);
        System.out.println("Maior grau: " + maior);
    }
}
