package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.PedidoModel;
import com.example.eccomerce.models.UserModel;
import com.example.eccomerce.repositories.PedidoRepository;
import com.example.eccomerce.resources.ToolsResource;
import com.example.eccomerce.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repositorio;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    ToolsResource tools;

    public List<PedidoModel> getAll(String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);

        if (myUser.getRole() == 0) {
            return myUser.getCliente().getPedidos();
        }

        return repositorio.findAll();
    }

    public PedidoModel get(String numero, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);

        Optional<PedidoModel> optional;
        if (myUser.getRole() == 0) {
            optional = repositorio.findByClienteAndNumero(myUser.getCliente(), numero);
        } else {
            optional = repositorio.findByNumero(numero);
        }

        if (optional.isEmpty()) {
            throw new ErrorException("Pedido não existe!");
        }

        return optional.get();
    }

    public PedidoModel add(String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyClientes(myUser);

        if (repositorio.existPedidoAberto(myUser.getCliente()) > 0) {
            throw new ErrorException("Você já tem um pedido aberto!");
        }

        PedidoModel novoPedido = new PedidoModel(myUser.getCliente());
        repositorio.save(novoPedido);

        return novoPedido;
    }

    public Void liberar(String numero, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyClientes(myUser);

        Optional<PedidoModel> optional = repositorio.findByClienteAndNumero(myUser.getCliente(), numero);
        if (optional.isEmpty()) {
            throw new ErrorException("Pedido não existe!");
        }

        PedidoModel pedido = optional.get();

        if (pedido.getProdutos().isEmpty()) {
            throw new ErrorException("Para finalizar adicione um produto!");
        }

        pedido.setEntrege(LocalDate.now());
        pedido.setStatus("Entregue");
        repositorio.save(pedido);
        return null;
    }

    public Void delete(String numero, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);

        Optional<PedidoModel> optional;
        if (myUser.getRole() == 0) {
            optional = repositorio.findByClienteAndNumero(myUser.getCliente(), numero);
        } else {
            optional = repositorio.findByNumero(numero);
        }

        if (optional.isEmpty()) {
            throw new ErrorException("Pedido não existe!");
        }

        repositorio.deleteById(optional.get().getId());
        return null;
    }
}
