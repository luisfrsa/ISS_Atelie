package controle;

public class jaja {

 /*1*/
 public Consultora salvar(Consultora consultora) {
    /*2*/
    Consultora novaConsultora = null;
    /*3*/
    String erro = validarConsultora(consultora);
    /*4*/
    if (isNull(erro)) {
        /*5*/
        if (isNull(consultora.getId())) {
            /*6*/
            novaConsultora = consultoraDAO.inserir(consultora);
        } else {
            /*7*/
            novaConsultora = consultoraDAO.alterar(consultora);
        }
        /*8*/
        fecharTela();
        /*9*/
        return novaConsultora;
       /*10*/
    } else {
        /*11*/
        JError.alert(erro, "Erro validação");
    }
    /*12*/
    return null;
}

public String validarConsultora(Consultora consultora) {
    /*13*/
    String erroMsg = null;
    /*14*/
    if (isNull(consultora.getNome()) || consultora.getNome().length() < 3) {
        /*15*/
        return "O Nome deve possuir no mínimo 3 caracteres";
    }
    /*16*/
    if (isNull(consultora.getNome()) || consultora.getNome().length() > 50) {
        /*17*/
        return "O Nome deve possuir no máximo 50 caracteres";
    }
    /*18*/
    if (isNull(consultora.getCpf()) || !validaCPF(consultora.getCpf())) {
        /*19*/
        return "CPF Inválido";
    }
    /*20*/
    if (isNull(consultora.getDataNascimento())) {
        /*21*/
        return "Não foi possível converter data de nascimento, favor informar dd/mm/yyyy";
    }
    /*22*/
    return validarCPFUnico(consultora);
}

/*23*/
private void fecharTela() {
    /*24*/
    atualizaTabela();
    /*25*/
    formGerenciarConsultora.setVisible(false);
}

/*26*/
private void atualizaTabela() {
    /*27*/
    List<Consultora> consultoras = consultoraDAO.buscarTodos();
    /*28*/
    preencheTabela(consultoras);
}

/*29*/
private void preencheTabela(List<Consultora> lista) {
    /*30*/
    DefaultTableModel modelo = formListarConsultora.getTblConsultora().getModel();
    /*31*/
    modelo.setNumRows(0);
    /*32*/
    lista.stream().forEach(consultora ->  {
        /*33*/
        modelo.addRow(new Object[] {
            consultora.getId(),
            consultora.getNome(),
            adicionaPontuacaoCPF(consultora.getCpf()),
            consultora.getStatusAtividade() ? "Ativo" : "Inativo"
         });
    });
}

/*34*/
private String validarCPFUnico(Consultora consultoraValidacao) {
    /*35*/
    List<Consultora> consultorasList = consultoraDAO.buscarTodos();
    /*36*/
    List<Consultora> consultorasRepetidas = consultorasList
        .stream()
        .filter(consultora -> consultora.getCpf().equals(consultoraValidacao.getCpf()))
        .collect(Collectors.toList());
    /*37*/
    for (Consultora repetida : consultorasRepetidas) {
        /*38*/
        if (!repetida.getId().equals(consultoraValidacao.getId())) {
            /*49*/
            return "Consultora " + repetida.getNome() + " já possui CPF " + repetida.getCpf();
        }
    }
    /*40*/
    return null;
}



}