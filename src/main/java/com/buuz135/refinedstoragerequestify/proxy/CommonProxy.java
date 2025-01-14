/*
 * This file is part of RSRequestifyu.
 *
 * Copyright 2021, Buuz135
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.buuz135.refinedstoragerequestify.proxy;

import com.buuz135.refinedstoragerequestify.RefinedStorageRequestify;
import com.buuz135.refinedstoragerequestify.proxy.block.BlockCraftingEmitter;
import com.buuz135.refinedstoragerequestify.proxy.block.BlockRequester;
import com.buuz135.refinedstoragerequestify.proxy.block.tile.TileCraftingEmitter;
import com.buuz135.refinedstoragerequestify.proxy.block.tile.TileRequester;
import com.buuz135.refinedstoragerequestify.proxy.container.ContainerCraftingEmitter;
import com.buuz135.refinedstoragerequestify.proxy.container.ContainerRequester;
import com.refinedmods.refinedstorage.container.factory.PositionalTileContainerFactory;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;

public class CommonProxy {

    public static BlockRequester REQUESTER;
    public static ContainerType<ContainerRequester> REQUESTER_CONTAINER = null;
    public static TileEntityType<?> REQUESTER_TYPE;

    public static BlockCraftingEmitter CRAFTING_EMITTER;
    public static TileEntityType<?> CRAFTING_EMITTER_TYPE;
    public static ContainerType<ContainerCraftingEmitter> CRAFTING_EMITTER_CONTAINER = null;

    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(REQUESTER = new BlockRequester());
        event.getRegistry().register(CRAFTING_EMITTER = new BlockCraftingEmitter());
    }

    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new BlockItem(REQUESTER, new Item.Properties().group(RefinedStorageRequestify.TAB)).setRegistryName(REQUESTER.getRegistryName()));
        event.getRegistry().register(new BlockItem(CRAFTING_EMITTER, new Item.Properties().group(RefinedStorageRequestify.TAB)).setRegistryName(CRAFTING_EMITTER.getRegistryName()));
    }

    public void registerTiles(RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().register(REQUESTER_TYPE = TileEntityType.Builder.create(TileRequester::new, REQUESTER).build(null).setRegistryName(REQUESTER.getRegistryName()));
        event.getRegistry().register(CRAFTING_EMITTER_TYPE = TileEntityType.Builder.create(TileCraftingEmitter::new, CRAFTING_EMITTER).build(null).setRegistryName(CRAFTING_EMITTER.getRegistryName()));
    }

    public void registerContainers(RegistryEvent.Register<ContainerType<?>> event) {
        event.getRegistry().register(REQUESTER_CONTAINER = (ContainerType<ContainerRequester>) IForgeContainerType.create(new PositionalTileContainerFactory<ContainerRequester, TileRequester>((i, playerInventory, tileRequester) -> new ContainerRequester(tileRequester, playerInventory.player, i))).setRegistryName(new ResourceLocation(RefinedStorageRequestify.MOD_ID, "requester")));
        event.getRegistry().register(CRAFTING_EMITTER_CONTAINER = (ContainerType<ContainerCraftingEmitter>) IForgeContainerType.create(new PositionalTileContainerFactory<ContainerCraftingEmitter, TileCraftingEmitter>((i, playerInventory, tile) -> new ContainerCraftingEmitter(tile, playerInventory.player, i))).setRegistryName(new ResourceLocation(RefinedStorageRequestify.MOD_ID, "crafting_emitter")));

    }
}
