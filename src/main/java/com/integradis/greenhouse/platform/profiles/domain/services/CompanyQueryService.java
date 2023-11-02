package com.integradis.greenhouse.platform.profiles.domain.services;

import com.integradis.greenhouse.platform.profiles.domain.model.aggregates.Company;
import com.integradis.greenhouse.platform.profiles.domain.model.queries.GetCompanyByCompanyNameQuery;
import com.integradis.greenhouse.platform.profiles.domain.model.queries.GetCompanyByIdQuery;
import com.integradis.greenhouse.platform.profiles.domain.model.queries.GetCompanyByTinQuery;

import java.util.Optional;

public interface CompanyQueryService {

    Optional<Company> handle(GetCompanyByIdQuery query);
    Optional<Company> handle(GetCompanyByCompanyNameQuery query);
    Optional<Company> handle(GetCompanyByTinQuery query);
}
