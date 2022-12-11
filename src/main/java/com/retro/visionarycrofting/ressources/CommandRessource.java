package com.retro.visionarycrofting.ressources;

import com.retro.visionarycrofting.entities.Command;
import com.retro.visionarycrofting.services.CommandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "commands")
public class CommandRessource {

    private final CommandService commandService;

    public CommandRessource(CommandService commandService) {
        this.commandService = commandService;
    }

    @GetMapping(path = "users/{username}/commands")
    public String getCommands(Model model, @PathVariable("username") String username){
        model.addAttribute("commands", commandService.getCommandsByClientUsername(username));
        return "commands";
    }

    @PostMapping
    public  Command addCommand(@RequestBody Command command){
      System.out.println(command);
        return  commandService.AddCommand(command);
    }

    @DeleteMapping(path = "commands/{id}")
    public String  deleteById(@PathVariable("id") Long id){
        commandService.deleteById(id);
        return "Deleted Successfully";
    }

    @PutMapping(path = "Commands/{id}")
    public Command  updateCommend(@RequestBody Command command , long id){
        return commandService.updateCommend(command , id);
    };
}
