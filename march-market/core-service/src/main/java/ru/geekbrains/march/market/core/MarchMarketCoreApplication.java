package ru.geekbrains.march.market.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarchMarketCoreApplication {
	// Домашнее задание:
	// 0. Разобраться с кодом
	// 1. Перенести все интеграции на WebClient
	// 2. Переделайте оформление заказа с использования Principal на использование
	// заголовка username

	public static void main(String[] args) {
		SpringApplication.run(MarchMarketCoreApplication.class, args);
	}
}
