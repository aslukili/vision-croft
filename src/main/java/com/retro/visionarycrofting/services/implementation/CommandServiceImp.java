package com.retro.visionarycrofting.services.implementation;

import com.retro.visionarycrofting.entities.*;
import com.retro.visionarycrofting.repositories.CommandRepository;
import com.retro.visionarycrofting.services.CartService;
import com.retro.visionarycrofting.services.ClientService;
import com.retro.visionarycrofting.services.CommandItemService;
import com.retro.visionarycrofting.services.CommandService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommandServiceImp implements CommandService {

    private final CommandRepository commandRepository ;
    private final CommandItemService commandItemService;
    private final ClientService clientService;
    private final CartService cartService;

    public CommandServiceImp(CommandRepository commandRepository, CommandItemService commandItemService, ClientService clientService, CartService cartService) {
        this.commandRepository = commandRepository;
        this.commandItemService = commandItemService;
        this.clientService = clientService;
      this.cartService = cartService;
    }




    @Override
    public List<Command> getCommands() {
        return commandRepository.findAll();
    }

    @Override
    public Command AddCommand(Command command, String sessionToken) {
      // verification:
      // check there is at least one existing command item in the list
//      assert  command.getCommandItems().size() >= 1;
//      // check there is enough quantity in each command item
//      for (CommandItem commandItem:
//        command.getCommandItems()) {
//        commandItemService.checkQuantity(commandItem, commandItem.getQuantite());
//      }


      // which cart has the checkout command?
      Cart cart = cartService.getShoppingCartBySessionToken(sessionToken);
      // who is the client?


      // convert card and its items to command and its items;
      List<CartItem> cartItems = cart.getItems();

      // convert cart item to command item
      for (CartItem cartItem:
           cartItems) {
        CommandItem commandItem = new CommandItem();
        commandItem.setProduct(cartItem.getProduct());
        commandItem.setQuantite(cartItem.getQuantity());
        //calculate price
        commandItem.setPrix(cartItem.getQuantity()*cartItem.getProduct().getPrix());
        //generate reference
        commandItem.setRef(UUID.randomUUID().toString());
        command.getCommandItems().add(commandItem);
      }
      Client client = clientService.findById(1).get();

      // convert cart to command
      command.setClient(client);
      command.setRef(UUID.randomUUID().toString());
      command.setDate(new Date());
      command.setPrixTotal(24.0);
      // save command,
      commandRepository.save(command);
      System.out.println("here");
      // save command items to db
      for (CommandItem commandItem:
      command.getCommandItems()) {
        commandItem.setCommand(command);
        commandItemService.addNew(commandItem);
      }



      // TODO: if the command is saved successfully we delete it from the card
      // return the final command
      return command;
    }

    @Override
    public void deleteById(long id) {
        commandRepository.deleteById(id);
    }

    @Override
    public Command updateCommend(Command command , long id) {
        Command Cmd  = commandRepository.findById(id).get();

        if (Objects.nonNull(command.getRef())
                && !"".equalsIgnoreCase(command.getRef())
        ){
            Cmd.setRef(command.getRef());
        }

        if (Objects.nonNull(command.getDate())){
            Cmd.getDate();
        }


        if (Objects.nonNull(command.getPrixTotal())){
            Cmd.setPrixTotal(command.getPrixTotal());
        }

        return commandRepository.save(Cmd);
    }


    public Command findByRef(String commandRef){
      return commandRepository.findByRef(commandRef);
    }

  @Override
  public List<Command> getCommandsByClientUsername(String username) {
    // find the client using its username:
    // TODO: make the following client optional!
    Client client = clientService.findByUsername(username);
    return commandRepository.findByClient(client);
  }

}
