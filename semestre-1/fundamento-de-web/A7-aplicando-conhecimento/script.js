const times = [
  {
    modalidade: "Futebol",
    nome: "Corinthians",
    jogadores: 32,
    cor: "preto",
    brasao: "assets/corinthians.png"
  },
  {
    modalidade: "Futebol",
    nome: "Palmeiras",
    jogadores: 42,
    cor: "verde",
    brasao: "assets/palmeiras.png"
  },
  {
    modalidade: "Futebol",
    nome: "São Paulo",
    jogadores: 38,
    cor: "vermelho",
    brasao: "assets/sao-paulo.png"
  },
  
];

function mostrarTodosOsTimes() {
  const container = document.getElementById("arrayInfo");
  container.innerHTML = `
    <ul>
      ${times.map(time => `
        <li>
          <strong>${time.nome}</strong> - ${time.modalidade}, 
          ${time.jogadores} jogadores, Cor: ${time.cor}
        </li>
      `).join("")}
    </ul>
  `;
}

function filtrarPorCor() {
  const corEscolhida = document.getElementById("corSelect").value;
  const filtroCor = document.getElementById("filtroCor");

  if (!corEscolhida) {
    filtroCor.innerHTML = "<p>Por favor, selecione uma cor.</p>";
    return;
  }

  const time = times.find(t => t.cor === corEscolhida);

  if (time) {
    filtroCor.innerHTML = `
      <h3>Time com cor "${corEscolhida}"</h3>
      <p><strong>Modalidade:</strong> ${time.modalidade}</p>
      <p><strong>Nome:</strong> ${time.nome}</p>
      <p><strong>Jogadores:</strong> ${time.jogadores}</p>
      <img src="${time.brasao}" alt="Brasão do time ${time.nome}" width="150">
    `;
  } else {
    filtroCor.innerHTML = `<p>Nenhum time com a cor "${corEscolhida}" foi encontrado.</p>`;
  }
}

mostrarTodosOsTimes();
