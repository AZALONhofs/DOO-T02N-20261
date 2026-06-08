package fag;

import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {

        //ATV1
        List<Integer> numeros = Arrays.asList(3, 8, 15, 22, 7, 14, 9, 30, 11, 6);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("------ ATV1 ------");
        System.out.println(pares);

        //ATV2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("\n------ ATV2 ------");
        System.out.println(nomesMaiusculos);

        //ATV3
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        System.out.println("\n------ ATV3 ------");
        contagem.forEach((palavra, qtd) -> System.out.println(palavra + ": " + qtd));

        //ATV4
        List<Produto> produtos = Arrays.asList(
                new Produto("Notebook", 3500.00),
                new Produto("Caneta", 5.00),
                new Produto("Mouse", 150.00),
                new Produto("Caderno", 45.00)
        );

        List<Produto> produtosFiltrados = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("\n------ ATV4 ------");
        produtosFiltrados.forEach(p -> System.out.println(p.getNome() + " - R$ " + p.getPreco()));

        //ATV5
        double total = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("\n------ ATV5 ------");
        System.out.printf("Total: R$ %.2f%n", total);

        //ATV6
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> ordenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("\n------ ATV6 ------");
        System.out.println(ordenadas);
    }
}

