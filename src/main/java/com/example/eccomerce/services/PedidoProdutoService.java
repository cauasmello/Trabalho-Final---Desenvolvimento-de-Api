package com.example.eccomerce.services;

import com.example.eccomerce.exceptions.ErrorException;
import com.example.eccomerce.models.*;
import com.example.eccomerce.repositories.PedidoProdutoRepository;
import com.example.eccomerce.repositories.PedidoRepository;
import com.example.eccomerce.repositories.ProdutoRepository;
import com.example.eccomerce.resources.ToolsResource;
import com.example.eccomerce.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoProdutoService {

    @Autowired
    PedidoProdutoRepository repositorio;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ImagemProdutoService imagemProdutoService;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    ToolsResource tools;

    public List<ProdutoDTOModel> get(String numero, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);

        Optional<PedidoModel> optional = pedidoRepository.findByClienteAndNumero(myUser.getCliente(), numero);
        if (optional.isEmpty()) {
            throw new ErrorException("Pedido não existe!");
        }

        List<ProdutoDTOModel> produtoDTOModels = new ArrayList<>();
        for(PedidoProdutoModel pedidoProdutoModel : optional.get().getProdutos()){
            produtoDTOModels.add(new ProdutoDTOModel(pedidoProdutoModel.getProduto(), imagemProdutoService.generateURL(pedidoProdutoModel.getProduto().getId())));
        }

        return produtoDTOModels;
    }

    public Void add(String numero, String token, PedidoProdutoDTOModel produto) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyClientes(myUser);

        if(produto.getProduto_id() == null){
            throw new ErrorException("Informe o id do produto");
        }

        if(produto.getQuantidade() == null){
            throw new ErrorException("Informe a quantidade do produto");
        }

        Optional<PedidoModel> optional = pedidoRepository.findByClienteAndNumero(myUser.getCliente(), numero);
        if (optional.isEmpty()) {
            throw new ErrorException("Pedido não existe!");
        }

        Optional<ProdutoModel> optionalProdutoModel = produtoRepository.findById(produto.getProduto_id());
        if (optionalProdutoModel.isEmpty()) {
            throw new ErrorException("Produto não existe!");
        }

        PedidoModel pedido = optional.get();
        if(!pedido.getStatus().equals("Aberto")){
            throw new ErrorException("Pedido fechado");
        }

        ProdutoModel produtoModel = optionalProdutoModel.get();

        Optional<PedidoProdutoModel> optionalPedidoProdutoModel = repositorio.findByPedidoAndProduto(pedido, produtoModel);
        if (optionalPedidoProdutoModel.isEmpty()) {
            if(produtoModel.getQuantidade_estoque() < produto.getQuantidade()){
                throw new ErrorException("Não tem essa quantidade no estoque");
            }
            Double pedido_valor = produtoModel.getValor()*produto.getQuantidade();
            PedidoProdutoModel pedidoProdutoModel = new PedidoProdutoModel(pedido_valor, produto.getQuantidade(), produtoModel, pedido);

            pedido.setValor(pedido.getValor()+pedido_valor);
            produtoModel.setQuantidade_estoque(produtoModel.getQuantidade_estoque()-produto.getQuantidade());

            repositorio.save(pedidoProdutoModel);
            pedidoRepository.save(pedido);
            produtoRepository.save(produtoModel);

            return null;
        }

        PedidoProdutoModel pedidoProdutoModel = optionalPedidoProdutoModel.get();
        if(produtoModel.getQuantidade_estoque() < produto.getQuantidade()){
            throw new ErrorException("Não tem essa quantidade no estoque");
        }
        Double pedido_valor = produtoModel.getValor()*produto.getQuantidade();

        pedidoProdutoModel.setValor(pedidoProdutoModel.getValor()+pedido_valor);
        pedidoProdutoModel.setQuantidade(pedidoProdutoModel.getQuantidade()+produto.getQuantidade());

        pedido.setValor(pedido.getValor()+pedidoProdutoModel.getValor());

        produtoModel.setQuantidade_estoque(produtoModel.getQuantidade_estoque()-produto.getQuantidade());

        repositorio.save(pedidoProdutoModel);
        pedidoRepository.save(pedido);
        produtoRepository.save(produtoModel);

        return null;
    }

    public Void delete(String numero, Integer produto, String token) throws ErrorException {
        UserModel myUser = jwtUtil.getLoggedUser(token);
        tools.existUser(myUser);
        tools.onlyClientes(myUser);

        Optional<PedidoModel> optional = pedidoRepository.findByClienteAndNumero(myUser.getCliente(), numero);
        if (optional.isEmpty()) {
            throw new ErrorException("Pedido não existe!");
        }

        Optional<ProdutoModel> optionalProdutoModel = produtoRepository.findById(produto);
        if (optionalProdutoModel.isEmpty()) {
            throw new ErrorException("Produto não existe!");
        }

        PedidoModel pedido = optional.get();
        if(!pedido.getStatus().equals("Aberto")){
            throw new ErrorException("Pedido fechado");
        }

        ProdutoModel produtoModel = optionalProdutoModel.get();

        Optional<PedidoProdutoModel> optionalPedidoProdutoModel = repositorio.findByPedidoAndProduto(pedido, produtoModel);
        if (optionalPedidoProdutoModel.isEmpty()) {
            throw new ErrorException("Seu pedido não tem esse produto");
        }

        PedidoProdutoModel pedidoProdutoModel = optionalPedidoProdutoModel.get();

        pedido.setValor(pedido.getValor()-pedidoProdutoModel.getValor());

        produtoModel.setQuantidade_estoque(produtoModel.getQuantidade_estoque()+pedidoProdutoModel.getQuantidade());

        repositorio.deleteById(pedidoProdutoModel.getId());
        pedidoRepository.save(pedido);
        produtoRepository.save(produtoModel);

        return null;
    }
}
