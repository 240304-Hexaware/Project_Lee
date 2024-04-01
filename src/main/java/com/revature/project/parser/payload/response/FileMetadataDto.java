package com.revature.project.parser.payload.response;

import com.revature.project.parser.models.FixedLengthFile;
import com.revature.project.parser.models.ParsedRecord;
import com.revature.project.parser.models.Specification;

public record FileMetadataDto(String id, FixedLengthFile flatFile, ParsedRecord parsedData, Specification specFile) {

}
