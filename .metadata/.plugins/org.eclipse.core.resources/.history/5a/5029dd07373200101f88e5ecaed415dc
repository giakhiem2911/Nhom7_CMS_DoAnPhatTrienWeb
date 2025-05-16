package nhom7.cms.ThiCuoiKy_Nhom7_CMS.service;

import nhom7.cms.ThiCuoiKy_Nhom7_CMS.model.MetaData;
import nhom7.cms.ThiCuoiKy_Nhom7_CMS.repository.MetaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetaDataService {

    @Autowired
    private MetaDataRepository metaDataRepository;

    public List<MetaData> getAllMetaData() {
        return metaDataRepository.findAll();
    }

    public Optional<MetaData> getMetaDataById(Long id) {
        return metaDataRepository.findById(id);
    }

    public MetaData saveMetaData(MetaData metaData) {
        return metaDataRepository.save(metaData);
    }

    public void deleteMetaData(Long id) {
        metaDataRepository.deleteById(id);
    }
}
