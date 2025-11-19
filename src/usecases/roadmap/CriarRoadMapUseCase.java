package usecases.roadmap;

import dao.RoadMapDao;
import dao.UserDao;
import entities.RoadMap;
import infra.ApiError;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class CriarRoadMapUseCase {
    private final UserDao userDao;
    private final RoadMapDao roadMapDao;

    public CriarRoadMapUseCase(RoadMapDao roadMapDao, UserDao userDao) {
        this.roadMapDao = roadMapDao;
        this.userDao = userDao;
    }

    public RoadMap execute(Long userId) throws SQLException {
        this.validateUser(userId);

        final var generatedDescription = this.generatedDescription();
        final var generatedTitle = this.generatedTitle(userId);
        final var roadMap = new RoadMap(userId, generatedDescription, generatedTitle);

        this.roadMapDao.cadastrar(roadMap);

        return roadMap;
    }

    private String generatedTitle(Long userId) {
        return String.format("Generated for %s at %s", userId, LocalDateTime.now());
    }

    private void validateUser(Long id) throws SQLException {
        this.userDao.pesquisar(id).orElseThrow(() -> new WebApplicationException(
                Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ApiError("Usuário não encontrado"))
                        .type(MediaType.APPLICATION_JSON)
                        .build()
        ));
    }

    private String generatedDescription() {
        return """
                <div style="font-family: Arial, Helvetica, sans-serif; line-height:1.45; color:#222;">
                  <h1 style="margin-bottom:0.2em;">Roteiro de Aprendizado Personalizado</h1>
                  <p><strong>Resumo:</strong> Com base no seu histórico, suas habilidades atuais e as exigências do mercado, aqui está um plano prático e progressivo para ampliar competências técnicas e soft skills, aumentar sua empregabilidade e gerar projetos para seu portfólio.</p>
                
                  <section>
                    <h2>1. Objetivos principais</h2>
                    <ul>
                      <li>Conquistar fundamentos sólidos em desenvolvimento back-end e front-end.</li>
                      <li>Dominar ferramentas de deploy e infraestrutura básica (containerização e CI/CD).</li>
                      <li>Melhorar comunicação, resolução de problemas e proficiência em inglês técnico.</li>
                    </ul>
                  </section>
                
                  <section>
                    <h2>2. Trilhas recomendadas (técnicas)</h2>
                    <ol>
                      <li><strong>Fundamentos (0–3 meses)</strong>: Java (ou JavaScript/Node), SQL, Git. Projeto: CRUD completo com autenticação.</li>
                      <li><strong>Profissionalização (3–6 meses)</strong>: Spring Boot / Express, RESTful APIs, testes automatizados (Jest/JUnit), integração com banco relacional e NoSQL. Projeto: API de gestão com testes e Dockerfile.</li>
                      <li><strong>Front-end e Deploy (6–9 meses)</strong>: React (ou tecnologia equivalente), consumo da API, pipelines CI/CD, Docker Compose. Projeto: Aplicação full-stack deployada em serviço cloud (Heroku / Render / VPS).</li>
                      <li><strong>Aprofundamento (9–12 meses)</strong>: Arquitetura limpa, design patterns, observability (logs/metrics), microserviços básicos. Projeto: Microserviço simples comunicando via REST/queue.</li>
                    </ol>
                  </section>
                
                  <section>
                    <h2>3. Soft skills essenciais</h2>
                    <ul>
                      <li><strong>Comunicação:</strong> escrever especificações e PRs claros.</li>
                      <li><strong>Trabalho em equipe:</strong> code review e colaboração em gitflow.</li>
                      <li><strong>Resolução de problemas:</strong> decompor bugs e propor hipóteses.</li>
                      <li><strong>Gestão de tempo:</strong> priorizar MVPs e entregas incrementais.</li>
                      <li><strong>Inglês técnico:</strong> leitura de documentação e comunicação assíncrona.</li>
                    </ul>
                  </section>
                
                  <section>
                    <h2>4. Plano de atividades semanais (modelo)</h2>
                    <table style="border-collapse:collapse; width:100%;">
                      <tr>
                        <td style="padding:6px; border:1px solid #ddd;"><strong>Segunda</strong></td>
                        <td style="padding:6px; border:1px solid #ddd;">Aprendizado teórico (1h)</td>
                      </tr>
                      <tr>
                        <td style="padding:6px; border:1px solid #ddd;"><strong>Quarta</strong></td>
                        <td style="padding:6px; border:1px solid #ddd;">Exercícios práticos / código (2h)</td>
                      </tr>
                      <tr>
                        <td style="padding:6px; border:1px solid #ddd;"><strong>Sábado</strong></td>
                        <td style="padding:6px; border:1px solid #ddd;">Projeto do portfólio (3h)</td>
                      </tr>
                    </table>
                  </section>
                
                  <section>
                    <h2>5. Recursos sugeridos</h2>
                    <ul>
                      <li>Plataformas: Coursera, Udemy, Alura, freeCodeCamp.</li>
                      <li>Leituras rápidas: documentação oficial, artigos Medium/Dev.to, RFCs quando aplicável.</li>
                      <li>Prática: GitHub — abrir issues, PRs e publicar projetos com README completo.</li>
                    </ul>
                  </section>
                
                  <section>
                    <h2>6. Entregáveis e métricas</h2>
                    <ul>
                      <li>2 projetos publicáveis no GitHub (1 back-end + 1 full-stack).</li>
                      <li>Testes cobrindo ≥ 60% do código crítico.</li>
                      <li>Perfil LinkedIn atualizado + resumo em inglês.</li>
                    </ul>
                  </section>
                
                  <section>
                    <h2>7. Próximos passos (curto prazo)</h2>
                    <ol>
                      <li>Escolher a primeira trilha técnica (ex: Java + SQL).</li>
                      <li>Definir o primeiro projeto MVP (CRUD + autenticação).</li>
                      <li>Configurar repositório com CI básico e README explicativo.</li>
                    </ol>
                  </section>
                
                  <footer style="margin-top:12px; font-size:0.95em; color:#555;">
                    <p><em>Observação:</em> este roteiro é mockado para simular o comportamento da IA. Para recomendações reais, o sistema analisaria seus cursos, experiências anteriores e objetivos específicos.</p>
                  </footer>
                </div>
                """;
    }
}
