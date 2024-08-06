package com.cerbon.beb.fabric;

import com.cerbon.beb.BeautifulEnchantedBooks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class BeautifulEnchantedBooksFabric implements ModInitializer, ClientModInitializer {

    @Override
    public void onInitialize() {
        BeautifulEnchantedBooks.init();
    }

    @Override
    public void onInitializeClient() {}
}