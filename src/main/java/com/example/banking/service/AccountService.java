package com.example.banking.service;

import com.example.banking.entity.Account;
import com.example.banking.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
	private final AccountRepository repo;

	public AccountService(AccountRepository repo) {
		this.repo = repo;
	}

	public Account createAccount(Account account) {
		return repo.save(account);
	}

	public List<Account> getAllAccounts() {
		return repo.findAll();
	}

	public Account deposit(Long id, double amount) {
		Account acc = repo.findById(id).orElseThrow();
		acc.setBalance(acc.getBalance() + amount);
		return repo.save(acc);
	}

	public Account withdraw(Long id, double amount) {
		Account acc = repo.findById(id).orElseThrow();
		if (acc.getBalance() >= amount) {
			acc.setBalance(acc.getBalance() - amount);
		} else {
			throw new RuntimeException("Insufficient funds!");
		}
		return repo.save(acc);
	}

	public void deleteAccount(Long id) {
		repo.deleteById(id);
	}
}