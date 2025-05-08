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

window.addEventListener("load", () => {
  const nome = prompt("Digite seu nome completo:");
  const cidade = prompt("Digite a cidade onde você estuda:");
  const cursoInstituicao = prompt("Digite seu curso e a instituição de ensino:");

  const cidadeCurso = `${cidade} — ${cursoInstituicao}`;

  const dataAtual = new Date();
  const hora = dataAtual.getHours();
  const diaSemana = dataAtual.toLocaleDateString("pt-BR", { weekday: "long" });
  const dataFormatada = dataAtual.toLocaleDateString("pt-BR");

  let saudacao;
  if (hora >= 6 && hora < 12) {
    saudacao = "Bom dia!";
  } else if (hora >= 12 && hora < 18) {
    saudacao = "Boa tarde!";
  } else {
    saudacao = "Boa noite!";
  }

  alert(
    `Olá, ${nome}!\n` +
    `Seja bem-vindo(a) — ${cidadeCurso}\n` +
    `${saudacao} Hoje é ${diaSemana}, ${dataFormatada}.`
  );
});
