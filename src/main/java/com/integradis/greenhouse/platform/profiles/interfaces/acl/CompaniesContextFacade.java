package com.integradis.greenhouse.platform.profiles.interfaces.acl;

import com.integradis.greenhouse.platform.profiles.domain.model.aggregates.Company;
import com.integradis.greenhouse.platform.profiles.domain.model.commands.CreateCompanyCommand;
import com.integradis.greenhouse.platform.profiles.domain.model.queries.GetCompanyByCompanyNameQuery;
import com.integradis.greenhouse.platform.profiles.domain.model.queries.GetCompanyByIdQuery;
import com.integradis.greenhouse.platform.profiles.domain.model.valueobjects.CompanyName;
import com.integradis.greenhouse.platform.profiles.domain.services.CompanyCommandService;
import com.integradis.greenhouse.platform.profiles.domain.services.CompanyQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompaniesContextFacade {
    private final CompanyCommandService companyCommandService;
    private final CompanyQueryService companyQueryService;

    public CompaniesContextFacade(CompanyCommandService companyCommandService, CompanyQueryService companyQueryService) {
        this.companyCommandService = companyCommandService;
        this.companyQueryService = companyQueryService;
    }

    public Long createCompany(String name, String tin){
        var createCompanyCommand = new CreateCompanyCommand(name, tin);
        return companyCommandService.handle(createCompanyCommand);
    }

    public Long getCompanyIdByCompanyName(String name){
        var getCompanyByCompanyNameQuery = new GetCompanyByCompanyNameQuery(new CompanyName(name));
        var company = companyQueryService.handle(getCompanyByCompanyNameQuery);
        if (company.isEmpty()) return 0L;
        return company.get().getId();
    }

    public Optional<Company> getCompanyById(Long id){
        var getCompanyByIdQuery = new GetCompanyByIdQuery(id);
        return companyQueryService.handle(getCompanyByIdQuery);
    }
}
