package planbServer.planb.sample.repository;

import org.apache.ibatis.annotations.Mapper;
import planbServer.planb.sample.model.SampleDTO;


import java.util.List;

@Mapper
public interface SampleDAO {

    List<SampleDTO> selectSampleDAO();
}
