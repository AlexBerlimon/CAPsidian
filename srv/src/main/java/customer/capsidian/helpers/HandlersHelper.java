package customer.capsidian.helpers;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.sap.cds.ql.cqn.AnalysisResult;
import com.sap.cds.ql.cqn.CqnAnalyzer;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.reflect.CdsModel;
import com.sap.cds.services.draft.DraftSaveEventContext;

@Component
public class HandlersHelper {
    public Map<String, Object> extractTargetKeys(DraftSaveEventContext context) {
        return process(context.getModel(), context.getCqn()).targetKeys();
    }

    private AnalysisResult process(CdsModel cdsModel, CqnSelect cqn) {
        CqnAnalyzer cqnAnalyzer = CqnAnalyzer.create(cdsModel);
        return cqnAnalyzer.analyze(cqn);
    }
}
