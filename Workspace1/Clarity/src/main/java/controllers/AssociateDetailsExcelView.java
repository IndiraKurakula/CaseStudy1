/*package controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import beans.AssociateDetails;

public class AssociateDetailsExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> data, HSSFWorkbook hsfwb, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		HSSFSheet sheet = hsfwb.createSheet("AssociateDetails");
		HSSFSheet sheet1 = hsfwb.createSheet("ClarityHours");
        int rowCount = 0;
        int headColumnCount = 0;
        HSSFRow header = sheet.createRow(rowCount);
        header.createCell(headColumnCount).setCellValue("AssociateID");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("HCSCID");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("AssociateName");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("ProjectName");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("Location");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("ClarityAccess");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("HCSCEmailID");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("PhoneNumber");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("HCSCJoiningDate");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("LastClarityUpdateDate");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("Rate");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("Revenue");
        headColumnCount++;
        
        header.createCell(headColumnCount).setCellValue("Date");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("Month");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("Hours");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("BacklogMonth");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("BacklogHours");
        headColumnCount++;
        header.createCell(headColumnCount).setCellValue("BacklogAmount");
        headColumnCount++;
        rowCount++;
        @SuppressWarnings("unchecked")
		List<AssociateDetails> assoDetailsList = (List<AssociateDetails>) data.get("AssociateDetails");
		for(AssociateDetails assoDetails:assoDetailsList){
			int dataColumn = 0;
            HSSFRow dataRow = sheet.createRow(rowCount++);
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getAssociateID());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getHCSCID());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getAssociateName());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getProjectName());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getLocation());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getClarityAccess());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getHCSCEmailID());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getPhoneNumber());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getHCSCJoiningDate());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getLastClarityUpdateDate());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getRate());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getRevenue());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getDate());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getMonth());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getHours());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getBacklogMonth());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getBacklogHours());
            dataColumn++;
            dataRow.createCell(dataColumn).setCellValue(assoDetails.getBacklogAmount());
            dataColumn++;
		}
	}

}
*/