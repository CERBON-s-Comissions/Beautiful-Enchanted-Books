package com.cerbon.beb.forge;

import com.cerbon.beb.BeautifulEnchantedBooks;
import com.cerbon.beb.util.BEBConstants;
import net.minecraftforge.fml.common.Mod;

@Mod(BEBConstants.MOD_ID)
public class BeautifulEnchantedBooksForge {

    public BeautifulEnchantedBooksForge() {
        BeautifulEnchantedBooks.init();
    }
}