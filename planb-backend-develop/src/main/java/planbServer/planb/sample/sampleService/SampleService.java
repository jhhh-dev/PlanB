package planbServer.planb.sample.sampleService;

import planbServer.planb.sample.model.SampleDTO;

import java.util.List;

public interface SampleService {
    public List<SampleDTO> selectSample();

    public List<SampleDTO> selectSampleParam(SampleDTO sampleDTO);
}
