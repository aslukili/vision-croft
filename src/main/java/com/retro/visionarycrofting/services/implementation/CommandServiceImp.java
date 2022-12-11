package com.retro.visionarycrofting.services.implementation;

import com.retro.visionarycrofting.entities.Client;
import com.retro.visionarycrofting.entities.Command;
import com.retro.visionarycrofting.entities.CommandItem;
import com.retro.visionarycrofting.entities.Product;
import com.retro.visionarycrofting.repositories.CommandRepository;
import com.retro.visionarycrofting.services.ClientService;
import com.retro.visionarycrofting.services.CommandItemService;
import com.retro.visionarycrofting.services.CommandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommandServiceImp implements CommandService {

    private final CommandRepository commandRepository ;
    private final CommandItemService commandItemService;
    private final ClientService clientService;

    public CommandServiceImp(CommandRepository commandRepository, CommandItemService commandItemService, ClientService clientService) {
        this.commandRepository = commandRepository;
        this.commandItemService = commandItemService;
        this.clientService = clientService;
    }


    @Override
    public List<Command> getCommands() {
        return commandRepository.findAll();
    }

    @Override
    public Command AddCommand(Command command) {
      // verification:
      // check there is at least one existing command item in the list
      assert  command.getCommandItems().size() >= 1;
      // check there is enough quantity in each command item
      for (CommandItem commandItem:
        command.getCommandItems()) {
        commandItemService.checkQuantity(commandItem, commandItem.getQuantite());
      }
      // who is the client?
      Optional<Client> client = clientService.findById(command.getClient().getId());

      command.setClient(client.orElseThrow(IllegalStateException::new));


      // save command,
      commandRepository.save(command);
      // save command items to db
      for (CommandItem commandItem:
      command.getCommandItems()) {
        commandItem.setCommand(command);
        commandItemService.addNew(commandItem);
      }



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

}
