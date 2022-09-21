package com.company.MainMarket;

import com.company.Model.Product;
import com.company.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Market {

    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Product> products;
    private static Map<Product, Integer> buyList;

    public static void main(String[] args) {
        products = new ArrayList<>();
        buyList = new HashMap<>();
        initialMain();
    }

    public static void initialMain(){

        System.out.println("-------------------------------------------");
        System.out.println("---------Menu inicial do mercado-----------");
        System.out.println("-------------------------------------------");
        System.out.println("***Selecione a opção abaixo que desejar ***");
        System.out.println("-------------------------------------------");
        System.out.println("|  Opção 1 - Cadastro de produtos         |");
        System.out.println("|  Opção 2 - Listar produtos              |");
        System.out.println("|  Opção 3 - Comprar                      |");
        System.out.println("|  Opção 4 - Carrinho                     |");
        System.out.println("|  Opção 5 - Sair                         |");

        int option = input.nextInt();

        switch (option){
            case 1:
                insertProducts();
                break;
            case 2:
                listProducts();
                break;
            case 3:
                buyProducts();
                break;
            case 4:
                shoppingCart();
                break;
            case 5:
                System.out.println("Obrigado pela preferência, volte sempre.");
                System.exit(0);
            default:
                System.out.println("Opção inválida!");
                initialMain();
                break;
        }
    }

    private static void insertProducts(){
        System.out.println("Nome do Produto: ");
        String name = input.next();

        System.out.println("Tipo do Produto: ");
        String type = input.next();

        System.out.println("Quantidade do Produto: ");
        Integer quantity = input.nextInt();

        System.out.println("Localização do Produto: ");
        String position = input.next();

        System.out.println("Preco do Produto: ");
        Double price = input.nextDouble();

        Product product = new Product(name, type, quantity, position, price);
        products.add(product);

        System.out.println("Produto "+product.getName()+
                " cadastrado com sucesso, no ID "+product.getId());
        initialMain();
    }

    private static void listProducts(){
        if (products.size() > 0){
            System.out.println("Lista de Produtos \n");

            for (Product p : products){
                System.out.println(p);
            }
        } else {
            System.out.println("Nenhum produto cadastrado!");
        }
        initialMain();
    }

    private static void buyProducts(){
        if (products.size() > 0){
            System.out.println("Código do Produto: \n");

            System.out.println("Produtos disponíveis: \n");
            for (Product p : products){
                System.out.println(p + "\n");
            }
            int id = Integer.parseInt(input.next());
            boolean isPresent = false;

            for (Product p : products){
                if (p.getId()==id) {
                    int qtt = 0;
                    try {
                        qtt = buyList.get(p);
                        buyList.put(p, qtt +1);
                    }catch (NullPointerException e) {
                        buyList.put(p, 1);
                    }
                    System.out.println(p.getName()+" adicionado ao carrinho.");
                    isPresent = true;

                    if (isPresent) {
                        System.out.println("Deseja adicionar mais algum produto?");
                        System.out.println("Digite 1 para  adicionar mais um produto");
                        System.out.println("Digite 2 para encerrar a compra!");
                        int option = Integer.parseInt(input.next());

                        if (option == 1){
                            buyProducts();
                        }else {
                            endShopping();
                        }
                    }
                }else{
                    System.out.println("Produto não encontrado!");
                    initialMain();
                }
            }
        }else{
            System.out.println("Produto não encontrado!");
            initialMain();
        }
    }

    private static void shoppingCart(){
        System.out.println("--Produtos no seu carrinho--");
        if (buyList.size() > 0){
            for (Product p : buyList.keySet()){
                System.out.println("Produto: " + p + "\nQuantidade: "+ buyList.get(p));
            }
        } else {
            System.out.println("Carrinho vazio!");
        }
        initialMain();
    }

    private static void endShopping(){
        Double totalPrice = 0.0;
        System.out.println("Produtos no carrinho:");

        for (Product p : buyList.keySet()){
            int qtt = buyList.get(p);
            totalPrice += p.getPrice() * qtt;
            System.out.println(p);
            System.out.println("Quantidade: "+qtt);
            System.out.println("-----------------");
        }
        System.out.println("O valor total da compra é: "+ Utils.doubleToString(totalPrice));
        buyList.clear();
        System.out.println("Obrigado pela preferência!");
        initialMain();
    }
}
