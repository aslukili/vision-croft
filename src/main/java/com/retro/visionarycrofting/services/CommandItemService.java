package com.retro.visionarycrofting.services;

import com.retro.visionarycrofting.entities.Command;
import com.retro.visionarycrofting.entities.CommandItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface CommandItemService {
  List<CommandItem> getCommandItems();

  CommandItem addNew(CommandItem commandItem);

  void deleteById(Long id);

  void updateCommandItem(Long commandItemId, String commandItemRef, int commandItemQuantity, double price);

  Optional<List<CommandItem>> findCommandItemsByCommand(String commandRef);

  void checkQuantity(CommandItem commandItem, int quantity);
}
