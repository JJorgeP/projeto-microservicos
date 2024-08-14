package br.com.jjorgeparrilla.pedidos.api.entity;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
	
	private UUID id  = UUID.randomUUID();
	private String nome;
	private Double valor;

}
