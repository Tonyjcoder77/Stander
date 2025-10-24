package br.com.osa.stander.service;

import br.com.osa.stander.dto.AgenciaRequest;
import br.com.osa.stander.dto.DistanceResponse;
import br.com.osa.stander.model.Agencia;
import br.com.osa.stander.repository.AgenciaRepository;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class AgenciaService {

    private final AgenciaRepository repo;
    private final CacheManager cacheManager;

    private final AtomicInteger insertCounter = new AtomicInteger(0);

    public AgenciaService(AgenciaRepository repo, CacheManager cacheManager) {
        this.repo = repo;
        this.cacheManager = cacheManager;
    }

    @Transactional
    public Long cadastrar(AgenciaRequest req) {
        Agencia ag = new Agencia(req.getPosX(), req.getPosY());
        ag = repo.save(ag);

        int curr = insertCounter.incrementAndGet();
        if (curr % 10 == 0) {
            var cache = cacheManager.getCache("agencias");
            if (cache != null) cache.clear();
        }
        return ag.getId();
    }

    @Cacheable("agencias")
    public List<Agencia> listaAgenciasComCache() {
        return repo.findAll();
    }

    public List<DistanceResponse> calcularDistanciasOrdenadas(int userX, int userY) {
        return listaAgenciasComCache().stream()
                .map(a -> new DistanceResponse(
                        a.nomeAgencia(),
                        distancia(userX, userY, a.getPosX(), a.getPosY())))
                .sorted(Comparator.comparingDouble(DistanceResponse::getDistancia))
                .collect(Collectors.toList());
    }

    private double distancia(int x1, int y1, int x2, int y2) {
        long dx = (long) x1 - x2;
        long dy = (long) y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
