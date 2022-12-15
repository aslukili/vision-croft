package com.retro.visionarycrofting.ressources;

import com.retro.visionarycrofting.entities.Command;
import com.retro.visionarycrofting.services.CartService;
import com.retro.visionarycrofting.services.CommandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class CommandRessource {

    private final CommandService commandService;
    private final CartService cartService;

    public CommandRessource(CommandService commandService, CartService cartService) {
        this.commandService = commandService;
      this.cartService = cartService;
    }

    @GetMapping(path = "users/{username}/commands")
    public String getCommands(Model model, @PathVariable("username") String username){
        model.addAttribute("commands", commandService.getCommandsByClientUsername(username));
        return "index";
    }


    // all we need from the client is the session id, and we will set the client manually for now:
    @PostMapping("/add-command")
    public  String addCommand(HttpServletRequest request, Model model){
      String sessionToken = (String) request.getSession(true).getAttribute("sessionToken");
      // get cart by session id
      commandService.AddCommand(new Command(), sessionToken);
      return  "redirect:/";
    }

    //

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
