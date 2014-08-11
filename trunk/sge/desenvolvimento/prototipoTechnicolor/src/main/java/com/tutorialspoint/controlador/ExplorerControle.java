package com.tutorialspoint.controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "explorerControle")
@ViewScoped
public class ExplorerControle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean exibir;
	
	private TreeNode root;
	private TreeNode selectedNode;
	
	private TreeNode rootMeusDocumentos;
	
	@PostConstruct
	public void init() {
		
		this.exibir = false;
		
		// Documentos Publicos
		root = new DefaultTreeNode("root", null);
		
		TreeNode documents = new DefaultTreeNode("Documents", root);
		
		TreeNode work = new DefaultTreeNode("Controle de Qualidade", documents);
		TreeNode work1 = new DefaultTreeNode("Controle de Fornecedores", documents);
		
		// Documents
		new DefaultTreeNode("document", "plano-de-qualidade.doc", work);
		new DefaultTreeNode("document", "RMA_CALCOMP.xlsx", work);
		new DefaultTreeNode("document", "Calendario 2014.xls", work);
		new DefaultTreeNode("document", "Relatorio-KPI.xls", work);
		new DefaultTreeNode("document", "Relatorio-8D.pdf", work);
		
		new DefaultTreeNode("document", "QE-012-Controle de Materiais Inspecionados MAR.2013.xlsx", work1);
		new DefaultTreeNode("document", "Plano de trabalho - IQC Report.doc", work1);
		new DefaultTreeNode("document", "QE_006 - Lista de fornecedores Nacionais e Importados a inspecionar - 13.08.2013.xlsx", work1);
		new DefaultTreeNode("document", "Quality Suppliers Evaluation Report 2013.xlsx", work1);
		new DefaultTreeNode("document", "Supplier Audit Plan -2013.xls", work1);
		
		// Meus Documentos
//		rootMeusDocumentos = new DefaultTreeNode("root", null);
//		
//		TreeNode pastaPessoal = new DefaultTreeNode("marcosoliveira", root);
//		
//		TreeNode emails = new DefaultTreeNode("Meus Emails", pastaPessoal);
//		TreeNode arquivosTemp = new DefaultTreeNode("Arquivos Temporarios", pastaPessoal);
//		
//		new DefaultTreeNode("document", "plano-de-qualidade.doc", emails);
//		new DefaultTreeNode("document", "RMA_CALCOMP.xlsx", emails);
//		new DefaultTreeNode("document", "Calendario 2014.xls", emails);
//		new DefaultTreeNode("document", "Relatorio-KPI.xls", emails);
//		new DefaultTreeNode("document", "Relatorio-8D.pdf", emails);
//		
//		new DefaultTreeNode("document", "Relatorio de Inspecionados-TEMP.xlsx", arquivosTemp);
//		new DefaultTreeNode("document", "Plano de trabalho - IQC Report-TEMP.doc", arquivosTemp);
//		new DefaultTreeNode("document", "RELATORIO KPI-TEMP.xls", arquivosTemp);	
	}
	
    public String onNodeSelectDocPublicos(NodeSelectEvent event) {
    	this.exibir = true;
    	this.selectedNode = event.getTreeNode();  
    	return "/paginas/documentopublico/pesquisa.xhtml";
    }
	
    public String onNodeSelectMeusDoc(NodeSelectEvent event) {
    	this.exibir = true;
    	this.selectedNode = event.getTreeNode();  
    	return "/paginas/meusdocumentos/pesquisa.xhtml";
    }
    
    public void onNodeExpand(NodeExpandEvent event) {  
    	this.selectedNode = event.getTreeNode();  
    }  
  
    public void onNodeCollapse(org.primefaces.event.NodeCollapseEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", event.getTreeNode().toString());  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	public TreeNode getRoot() {
		return root;
	}
	
	public void setRoot(TreeNode root) {
	    this.root = root;
	}
	
    public TreeNode getSelectedNode() {
	        return selectedNode;
    }
	 
	public void setSelectedNode(TreeNode selectedNode) {
	    this.selectedNode = selectedNode;
	}
	
	public TreeNode getRootMeusDocumentos() {
        return rootMeusDocumentos;
	}
	 
	public void setRootMeusDocumentos(TreeNode rootMeusDocumentos) {
	    this.rootMeusDocumentos = rootMeusDocumentos;
	}

	public boolean isExibir() {
		return exibir;
	}

	public void setExibir(boolean exibir) {
		this.exibir = exibir;
	}
}