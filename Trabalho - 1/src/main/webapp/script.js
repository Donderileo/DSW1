function doToggle(){
	pro = document.getElementById("pro");
	cli = document.getElementById("cli");
	
	btn = document.getElementById("btn");
	logCli = document.getElementById("logCli");
	logPro = document.getElementById("logPro");

	  pro.classList.toggle("none");
	  cli.classList.toggle("none");
	  
	  logPro.classList.toggle("none");
	  logCli.classList.toggle("none");
	  
	
}

