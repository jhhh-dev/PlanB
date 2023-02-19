package planbServer.planb.sample.sampleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planbServer.planb.sample.model.SampleDTO;
import planbServer.planb.sample.repository.SampleDAO;

import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    private SampleDAO sampleDAO;

    @Override
    public List<SampleDTO> selectSample() {
        return sampleDAO.selectSampleDAO();
    }

    @Override
    public List<SampleDTO> selectSampleParam(SampleDTO sampleDTO) {
        return sampleDAO.selectSampleDAO();
    }
}
