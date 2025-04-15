const toggleButton = document.querySelector(".menu-toggle");
   const navLinks = document.querySelector(".nav-links");

   toggleButton.addEventListener("click", () => {
       navLinks.classList.toggle("open");
   });

   let nomeCompleto = "Nome Completo Fictício";
   let anoCorrente = "2025";
   let twitter = "@twitterficticio";
   let facebook = "@facebookficticio";
   let instagram = "@instagramficticio";
   let linkedin = "@linkedinficticio";
   let sexo = "Não Especificado";
   let localizacaoPolo = "Polo Fictício";

   let rodapeDiv = document.getElementById("dados-rodape");

   if (rodapeDiv) { 
       rodapeDiv.innerHTML = `
           <p>Nome Completo: ${nomeCompleto}</p>
           <p>Ano: ${anoCorrente}</p>
           <p>
               <i class="fab fa-twitter"></i> ${twitter}
           </p>
           <p>
               <i class="fab fa-facebook"></i> ${facebook}
           </p>
           <p>
               <i class="fab fa-instagram"></i> ${instagram}
           </p>
           <p>
               <i class="fab fa-linkedin"></i> ${linkedin}
           </p>
           <p>Sexo: ${sexo}</p>
           <p>Localização Polo: ${localizacaoPolo}</p>
       `;
   }