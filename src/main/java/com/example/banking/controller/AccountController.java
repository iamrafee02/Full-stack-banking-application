package com.example.banking.controller;

import com.example.banking.entity.Account;
import com.example.banking.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public String listAccounts(Model model) {
        model.addAttribute("accounts", service.getAllAccounts());
        return "list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("account", new Account());
        return "create";
    }

    @PostMapping("/create")
    public String createAccount(@ModelAttribute Account account) {
        service.createAccount(account);
        return "redirect:/accounts";
    }

    @GetMapping("/deposit/{id}")
    public String depositForm(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam Long id, @RequestParam double amount) {
        service.deposit(id, amount);
        return "redirect:/accounts";
    }

    @GetMapping("/withdraw/{id}")
    public String withdrawForm(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Long id, @RequestParam double amount) {
        service.withdraw(id, amount);
        return "redirect:/accounts";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        service.deleteAccount(id);
        return "redirect:/accounts";
    }
}