package com.drissT.reddit;

import com.drissT.reddit.RedditClone.configuration.SpringFoxConfig;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
@Import(SpringFoxConfig.class)
public class RedditApplication implements CommandLineRunner
{
	
	public static void main(String[] args) 
	{
		SpringApplication.run(RedditApplication.class, args);
	}
   
	@Override
	public void run(String... args) throws Exception 
	{
		// TODO Auto-generated method stub
		// Client c1=clientRepo.save(new Client(1L,"Khalid",null));
		// Client c2=clientRepo.save(new Client(2L,"Oualid",null));
		// Date d=new Date();
		// Compte cp1=compteRepo.save(new CompteCourant("c1", d,90000,c1,5000));
		// Compte cp2=compteRepo.save(new CompteEpargne("c2",d,4000,c2,5.5));
		// Operation v=Verement.builder().date(d).montant(500).c(cp1).build();
		// operationRepo.save(v);
		// Operation v2=Verement.builder().date(d).montant(2300).c(cp1).build();
		// operationRepo.save(v2);
		// Operation r=Retrait.builder().compte(cp1).date(d).montant(4000).build();
		// operationRepo.save(r);
		
		// banqueService.verser(cp2.getNumCompte(), 11111);
	}

}
