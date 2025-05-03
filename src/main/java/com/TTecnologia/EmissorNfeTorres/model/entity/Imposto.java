package com.TTecnologia.EmissorNfeTorres.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Imposto {

    @Column(name = "impost_icms")
    private Double aliquotaIcms;

    @Column(name = "impost_pis")
    private Double aliquotaPis;

    @Column(name = "impost_cofins")
    private Double aliquotaCofins;

    @Column(name = "impost_ipi")
    private Double aliquotaIpi;

    public Double getAliquotaIcms() {
        return aliquotaIcms;
    }

    public void setAliquotaIcms(Double aliquotaIcms) {
        this.aliquotaIcms = aliquotaIcms;
    }

    public Double getAliquotaPis() {
        return aliquotaPis;
    }

    public void setAliquotaPis(Double aliquotaPis) {
        this.aliquotaPis = aliquotaPis;
    }

    public Double getAliquotaCofins() {
        return aliquotaCofins;
    }

    public void setAliquotaCofins(Double aliquotaCofins) {
        this.aliquotaCofins = aliquotaCofins;
    }

    public Double getAliquotaIpi() {
        return aliquotaIpi;
    }

    public void setAliquotaIpi(Double aliquotaIpi) {
        this.aliquotaIpi = aliquotaIpi;
    }

    public static Double calcularIcms(Double precoProduto, Double aliquotaICMS){
        return  (precoProduto * aliquotaICMS);
    }

    public static Double calcularIpi(Double precoProduto, Double aliquotaIpi){
        return  (precoProduto * aliquotaIpi);
    }

    public static Double calcularPis(Double precoProduto, Double aliquotaPis){
        return  (precoProduto * aliquotaPis);
    }

    public static Double calcularCofins(Double precoProduto, Double aliquotaCofins){
        return  (precoProduto * aliquotaCofins);
    }

    public static Double setupImpostos(Produto produto, Double valorTotal){
        Imposto imposto = produto.getImposto();

        Double icms = calcularIcms(imposto.getAliquotaIcms()/100, valorTotal);
        Double cofins = calcularCofins(imposto.getAliquotaCofins()/100, valorTotal);
        Double pis = calcularPis(imposto.getAliquotaPis()/100, valorTotal);
        Double ipi = calcularIpi(imposto.getAliquotaIpi()/100, valorTotal);

        return icms + cofins + pis + ipi;
    }
}
