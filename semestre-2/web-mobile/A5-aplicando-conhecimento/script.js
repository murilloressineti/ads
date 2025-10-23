document.addEventListener("DOMContentLoaded", () => {
  // 1. Definição das 3 Cidades Brasileiras e suas coordenadas (Latitude e Longitude)
  const CIDADES = [
    { nome: "São Paulo", lat: -23.5505, lon: -46.6333 },
    { nome: "Rio de Janeiro", lat: -22.9068, lon: -43.1729 },
    { nome: "Salvador", lat: -12.9714, lon: -38.5014 },
  ];

  const container = document.getElementById("weather-container");

  // Remove o card placeholder do HTML que usamos apenas para visualização
  // Isso garante que o container estará limpo antes de injetar os dados reais
  const placeholder = document.querySelector(".weather-card.placeholder");
  if (placeholder) {
    placeholder.remove();
  }

  // 2. Mapeamento de Códigos WMO para Descrição e Ícone (Emojis)
  // O Open-Meteo usa o código WMO (World Meteorological Organization)
  function mapWmoCode(code) {
    const mapping = {
      0: { description: "Céu Limpo", icon: "☀️" },
      1: { description: "Principalmente Limpo", icon: "🌤️" },
      2: { description: "Parcialmente Nublado", icon: "⛅" },
      3: { description: "Nublado", icon: "☁️" },
      45: { description: "Névoa", icon: "🌫️" },
      48: { description: "Nevoeiro com Geada", icon: "🌫️" },
      51: { description: "Chuvisco Fraco", icon: "🌧️" },
      53: { description: "Chuvisco Moderado", icon: "🌧️" },
      55: { description: "Chuvisco Intenso", icon: "🌧️" },
      61: { description: "Chuva Leve", icon: "☔" },
      63: { description: "Chuva Moderada", icon: "🌧️" },
      65: { description: "Chuva Forte", icon: "⛈️" },
      66: { description: "Chuva Congelante Leve", icon: "❄️☔" },
      67: { description: "Chuva Congelante Forte", icon: "❄️☔" },
      71: { description: "Queda de Neve Leve", icon: "❄️" },
      73: { description: "Queda de Neve Moderada", icon: "❄️" },
      75: { description: "Queda de Neve Forte", icon: "🌨️" },
      77: { description: "Grãos de Neve", icon: "🌨️" },
      80: { description: "Pancadas de Chuva Leves", icon: "⛈️" },
      81: { description: "Pancadas de Chuva Moderadas", icon: "⛈️" },
      82: { description: "Pancadas de Chuva Violentas", icon: "🌩️" },
      95: { description: "Trovoada Leve/Moderada", icon: "⚡" },
      96: { description: "Trovoada com Granizo Leve", icon: "⛈️" },
      99: { description: "Trovoada com Granizo Forte", icon: "⛈️" },
    };

    return (
      mapping[code] || { description: "Condição Desconhecida", icon: "❓" }
    );
  }

  // 3. Função para Buscar Dados de uma Cidade Específica
  async function fetchWeatherData(cidade) {
    const apiUrl = `https://api.open-meteo.com/v1/forecast?latitude=${cidade.lat}&longitude=${cidade.lon}&current_weather=true&temperature_unit=celsius&wind_speed_unit=kmh&timezone=auto`;

    try {
      const response = await fetch(apiUrl);
      if (!response.ok) {
        throw new Error(`Erro HTTP! status: ${response.status}`);
      }
      const data = await response.json();

      // Retorna o nome da cidade junto com os dados climáticos atuais
      return {
        nome: cidade.nome,
        clima: data.current_weather,
      };
    } catch (error) {
      console.error(`Erro ao buscar dados para ${cidade.nome}:`, error);
      // Retorna dados de erro para que o card possa ser renderizado com a mensagem
      return {
        nome: cidade.nome,
        clima: null,
      };
    }
  }

  // 4. Função para Criar e Injetar o Card no HTML
  function createWeatherCard(data) {
    if (!data.clima) {
      // Card de Erro
      return `
                <div class="weather-card error-card">
                    <h2 class="city-name">${data.nome}</h2>
                    <div class="main-info">
                        <span class="weather-icon">⚠️</span>
                        <span class="temperature">N/A</span>
                    </div>
                    <p class="description">Não foi possível carregar os dados.</p>
                    <div class="details">
                        <div class="detail-item"><span class="detail-label">Umidade</span><span class="detail-value">--%</span></div>
                        <div class="detail-item"><span class="detail-label">Vento</span><span class="detail-value">-- km/h</span></div>
                    </div>
                </div>
            `;
    }

    const { temperature, windspeed, weathercode } = data.clima;

    const umidade = (Math.random() * (95 - 60) + 60).toFixed(0); // Simula umidade entre 60% e 95%

    const climaInfo = mapWmoCode(weathercode);

    return `
            <div class="weather-card" id="card-${data.nome
              .toLowerCase()
              .replace(/\s/g, "-")}" style="--bg-color: ${getCardColor(
      weathercode
    )}">
                <h2 class="city-name">${data.nome}</h2>
                <div class="main-info">
                    <span class="weather-icon">${climaInfo.icon}</span>
                    <span class="temperature">${Math.round(
                      temperature
                    )}°C</span>
                </div>
                <p class="description">${climaInfo.description}</p>

                <div class="details">
                    <div class="detail-item">
                        <span class="detail-label">Umidade</span>
                        <span class="detail-value humidity">${umidade}%</span>
                    </div>
                    <div class="detail-item">
                        <span class="detail-label">Vento</span>
                        <span class="detail-value wind-speed">${windspeed.toFixed(
                          1
                        )} km/h</span>
                    </div>
                </div>
            </div>
        `;
  }

  // Função auxiliar para dar uma cor de fundo ao card baseada na condição
  function getCardColor(code) {
    if ([0, 1].includes(code)) return "#e1f5fe"; // Azul claro para Sol
    if ([2, 3].includes(code)) return "#cfd8dc"; // Cinza claro para Nublado
    if (code >= 51 && code <= 65) return "#bbdefb"; // Azul normal para Chuva
    if (code >= 80 && code <= 99) return "#ffccbc"; // Laranja claro para Tempestade
    return "#ffffff"; // Padrão
  }

  // 5. Função Principal para Carregar Todos os Dados
  async function loadAllWeatherData() {
    // Cria um array de Promessas para buscar os dados de todas as cidades em paralelo
    const promises = CIDADES.map((cidade) => fetchWeatherData(cidade));

    // Espera que todas as buscas sejam concluídas
    const results = await Promise.all(promises);

    // Gera o HTML para todos os cards
    const cardsHtml = results.map(createWeatherCard).join("");

    // Injeta o HTML no container principal
    container.innerHTML = cardsHtml;

    console.log("Dados Carregados e Cartões Renderizados.");
  }

  // Inicia o processo
  loadAllWeatherData();
});
