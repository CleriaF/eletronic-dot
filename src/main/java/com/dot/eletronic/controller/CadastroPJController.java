package com.dot.eletronic.controller;

import com.dot.eletronic.dtos.CadastroPJDto;
import com.dot.eletronic.entities.Empresa;
import com.dot.eletronic.entities.Funcionario;
import com.dot.eletronic.enums.PerfilEnum;
import com.dot.eletronic.response.Response;
import com.dot.eletronic.services.EmpresaService;
import com.dot.eletronic.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "*")
public class CadastroPJController {


    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private EmpresaService empresaService;

    public CadastroPJController() {}

    @GetMapping("teste")
    public ResponseEntity<String> teste() {
        return new ResponseEntity<>("teste", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response<CadastroPJDto>> cadastrar(@Valid @RequestBody CadastroPJDto cadastroPJDto,
                                                             BindingResult result) {
        Response<CadastroPJDto> response = new Response<CadastroPJDto>();

        validarDadosExistentes(cadastroPJDto, result);
        Empresa empresa = this.converterDtoParaEmpresa(cadastroPJDto);
        Funcionario funcionario = this.converterDtoParaFuncionario(cadastroPJDto, result);

        if(result.hasErrors()){
            result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        this.empresaService.persistir(empresa);
        funcionario.setEmpresa(empresa);
        funcionarioService.persistir(funcionario);

        response.setData(this.converterCadastroPJDto(funcionario));
        return ResponseEntity.ok(response);

    }

    private void validarDadosExistentes(CadastroPJDto cadastroPJDto, BindingResult result) {
        this.empresaService.buscarPorCnpj(cadastroPJDto.getCnpj())
                .ifPresent(empresa -> result.addError(new ObjectError("empresa", "Empresa já existente.")));

        this.funcionarioService.buscarPorCpf(cadastroPJDto.getCpf())
                .ifPresent(funcionario -> result.addError(new ObjectError("funcionario", "CPF já existente.")));

        this.funcionarioService.buscarPorEmail(cadastroPJDto.getEmail())
                .ifPresent(funcionario -> result.addError(new ObjectError("funcionario", "Email já existente.")));
    }

    private Empresa converterDtoParaEmpresa(CadastroPJDto cadastroPJDto) {
        Empresa empresa = new Empresa();
        empresa.setCnpj(cadastroPJDto.getCnpj());
        empresa.setRazaoSocial(cadastroPJDto.getRazapSocial());

        return empresa;
    }

    private Funcionario converterDtoParaFuncionario(CadastroPJDto cadastroPJDto, BindingResult result)
        {

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(cadastroPJDto.getNome());
        funcionario.setEmail(cadastroPJDto.getEmail());
        funcionario.setCpf(cadastroPJDto.getCpf());
        funcionario.setPerfilEnum(PerfilEnum.ROLE_ADMIN);
        funcionario.setSenha(cadastroPJDto.getSenha());

        return funcionario;

    }

    private CadastroPJDto converterCadastroPJDto(Funcionario funcionario){

        CadastroPJDto cadastroPJDto = new CadastroPJDto();

        cadastroPJDto.setId(funcionario.getId());
        cadastroPJDto.setNome(funcionario.getNome());
        cadastroPJDto.setEmail(funcionario.getEmail());
        cadastroPJDto.setCnpj(funcionario.getCpf());
        cadastroPJDto.setRazapSocial(funcionario.getEmpresa().getRazaoSocial());
        cadastroPJDto.setCnpj(funcionario.getEmpresa().getCnpj());

        return cadastroPJDto;
    }


}
