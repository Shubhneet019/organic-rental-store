package com.training.ifaces;

import java.util.List;

import com.training.model.Item;

public interface Report {
void addItem(int itemCode, int quantity);
boolean entryToDatabase(Item item,int quantity);
List<Item> dailyReport();
List<Item> monthlyReport();
List<Item> monthlyReportByCategory();
}