
package com.sd.locacao;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/locacao")
public class LocacaoController {

    private List<Aparelho> aparelhos = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    // Cadastrar Cliente
    @PostMapping("/clientes")
    public String cadastrarCliente(@RequestBody Cliente cliente) {
        clientes.add(cliente);
        return "Cliente cadastrado com sucesso: " + cliente.getNome();
    }

    //Listar Clientes
    @GetMapping("/clientes")
    public List<Cliente> listarClientes() {
        return clientes;
    }

    // Cadastrar Aparelho
    @PostMapping("/aparelhos")
    public String cadastrarAparelho(@RequestBody Aparelho aparelho) {
        aparelhos.add(aparelho);
        return "Aparelho cadastrado com sucesso: " + aparelho.getNome();
    }

    // Listar Aparelhos
    @GetMapping("/aparelhos")
    public List<Aparelho> listarAparelhos() {
        return aparelhos;
    }

    @PostMapping("/clientes/{cpf}/alugar")
    public String alugarAparelhos(@PathVariable String cpf, @RequestBody List<String> nomesAparelhos) {
        // Encontrar o cliente pelo CPF
        Cliente cliente = clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + cpf));

        // Encontrar os aparelhos pelo nome e adicioná-los ao cliente
        for (String nomeAparelho : nomesAparelhos) {
            Aparelho aparelho = aparelhos.stream()
                    .filter(a -> a.getNome().equals(nomeAparelho))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Aparelho não encontrado: " + nomeAparelho));

            cliente.alugarAparelho(aparelho);
        }

        return "Aparelhos alugados com sucesso para o cliente: " + cliente.getNome();
    }
    // Listar Aparelhos Alugados
    @GetMapping("/clientes/{cpf}/aparelhos")
    public List<Aparelho> listarAparelhosAlugados(@PathVariable String cpf) {
        Cliente cliente = clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + cpf));

        return cliente.getAparelhosAlugados();
    }

    // Deletar Aparelho
    @DeleteMapping("/aparelhos/{nome}")
    public String deletarAparelho(@PathVariable String nome) {
        for (Aparelho aparelho : aparelhos) {
            if (aparelho.getNome().equals(nome)) {
                aparelhos.remove(aparelho);
                return "Aparelho deletado com sucesso: " + nome;
            }
        }
        return "Aparelho não encontrado: " + nome;
    }

    // Calcular Locação
    @GetMapping("/calcularLocacao")
    public String calcularLocacao(@RequestParam String nomeAparelho, @RequestParam int dias) {
        for (Aparelho aparelho : aparelhos) {
            if (aparelho.getNome().equals(nomeAparelho)) {
                double valorTotal = aparelho.getPrecoLocacao() * dias;
                return "Locação do aparelho " + aparelho.getNome() + " por " + dias + " dias. Valor total: R$ "
                        + valorTotal;
            }
        }
        return "Aparelho não encontrado: " + nomeAparelho;
    }
}
