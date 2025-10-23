const ACCESS_TOKEN = "a1f8d81ce0874a28e26f62856716cc06";
const BASE_URL = `https://superheroapi.com/api/${ACCESS_TOKEN}`;

const MIN_ID = 1;
const MAX_ID = 731;

function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

function selecionarIdsAleatorios() {
  let id1 = getRandomInt(MIN_ID, MAX_ID);
  let id2 = getRandomInt(MIN_ID, MAX_ID);

  while (id1 === id2) {
    id2 = getRandomInt(MIN_ID, MAX_ID);
  }

  return [id1, id2];
}

async function buscarCard(id) {
  try {
    const urlStats = `${BASE_URL}/${id}/powerstats`;
    const urlImage = `${BASE_URL}/${id}/image`;

    const [resStats, resImage] = await Promise.all([
      fetch(urlStats),
      fetch(urlImage),
    ]);

    const stats = await resStats.json();
    const image = await resImage.json();

    if (stats.response === "error" || image.response === "error") {
      console.warn(
        `Aviso: Herói ID ${id} não encontrado ou dados incompletos. Pulando.`
      );
      return null;
    }

    return {
      id: stats.id,
      name: stats.name,
      powerstats: {
        intelligence: parseInt(stats.intelligence) || 0,
        strength: parseInt(stats.strength) || 0,
        speed: parseInt(stats.speed) || 0,
        durability: parseInt(stats.durability) || 0,
        power: parseInt(stats.power) || 0,
        combat: parseInt(stats.combat) || 0,
      },
      imageUrl: image.url,
    };
  } catch (error) {
    console.error(`Erro ao buscar o herói ID ${id}:`, error.message);
    return null;
  }
}

async function buscarDuasCartas() {
  let card1, card2;
  let attempts = 0;
  const maxAttempts = 5;

  do {
    attempts++;
    const [id1, id2] = selecionarIdsAleatorios();
    console.log(`Tentativa ${attempts}: Buscando IDs ${id1} e ${id2}...`);

    [card1, card2] = await Promise.all([buscarCard(id1), buscarCard(id2)]);
  } while ((!card1 || !card2) && attempts < maxAttempts);

  if (!card1 || !card2) {
    throw new Error(
      "Não foi possível carregar duas cartas válidas após várias tentativas."
    );
  }

  return [card1, card2];
}

function compararCartas(cardA, cardB, attribute) {
  const valueA = cardA.powerstats[attribute] || 0;
  const valueB = cardB.powerstats[attribute] || 0;

  if (valueA > valueB) {
    return cardA.name;
  } else if (valueB > valueA) {
    return cardB.name;
  } else {
    return "Empate";
  }
}

async function iniciarComparacao(atributoParaComparar) {
  try {
    console.log("Iniciando a comparação de cartas...");

    const [card1, card2] = await buscarDuasCartas();

    const vencedor = compararCartas(card1, card2, atributoParaComparar);

    console.log("\n=============================================");
    console.log("              RESULTADO DA RODADA");
    console.log("=============================================");

    console.log(`\n🦸 Card 1: ${card1.name} (ID: ${card1.id})`);
    console.log(`\n🦹 Card 2: ${card2.name} (ID: ${card2.id})`);

    console.log(
      `\nAtributo Escolhido para Disputa: **${atributoParaComparar.toUpperCase()}**`
    );
    console.log(
      `  -> ${card1.name}: ${card1.powerstats[atributoParaComparar]}`
    );
    console.log(
      `  -> ${card2.name}: ${card2.powerstats[atributoParaComparar]}`
    );

    console.log("\n=============================================");
    if (vencedor === "Empate") {
      console.log(
        `Resultado: 🤝 EMPATE! As cartas têm o mesmo valor de ${atributoParaComparar}.`
      );
    } else {
      console.log(`Resultado: 🏆 O VENCEDOR é **${vencedor.toUpperCase()}**!`);
    }
    console.log("=============================================");
  } catch (error) {
    console.error("\nFALHA FATAL:", error.message);
  }
}

const atributo = "strength";

iniciarComparacao(atributo);
