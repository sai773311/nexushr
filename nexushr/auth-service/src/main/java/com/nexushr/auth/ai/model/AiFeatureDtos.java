package com.nexushr.auth.ai.model;

import java.util.List;
import java.util.Map;

/**
 * DTO records for all 16 NexusHR AI features.
 * Using nested static classes so the entire set lives in one file.
 */
public final class AiFeatureDtos {

    private AiFeatureDtos() {}

    // ── 1. AI HR Assistant ──────────────────────────────────────
    public static class AssistantRequest {
        private String question;
        private String role;
        private String email;
        public String getQuestion() { return question; }
        public void setQuestion(String v) { question = v; }
        public String getRole() { return role; }
        public void setRole(String v) { role = v; }
        public String getEmail() { return email; }
        public void setEmail(String v) { email = v; }
    }

    public static class AssistantResponse {
        private String question;
        private String domain;
        private String answer;
        private String recommendation;
        private String riskLevel;
        private boolean accessDenied;
        private String deniedReason;
        public String getQuestion() { return question; }
        public void setQuestion(String v) { question = v; }
        public String getDomain() { return domain; }
        public void setDomain(String v) { domain = v; }
        public String getAnswer() { return answer; }
        public void setAnswer(String v) { answer = v; }
        public String getRecommendation() { return recommendation; }
        public void setRecommendation(String v) { recommendation = v; }
        public String getRiskLevel() { return riskLevel; }
        public void setRiskLevel(String v) { riskLevel = v; }
        public boolean isAccessDenied() { return accessDenied; }
        public void setAccessDenied(boolean v) { accessDenied = v; }
        public String getDeniedReason() { return deniedReason; }
        public void setDeniedReason(String v) { deniedReason = v; }
    }

    // ── 2. AI Resume Screening ──────────────────────────────────
    public static class ResumeScreenRequest {
        private String candidateName;
        private String resumeText;
        private String targetJobTitle;
        private List<String> requiredSkills;
        public String getCandidateName() { return candidateName; }
        public void setCandidateName(String v) { candidateName = v; }
        public String getResumeText() { return resumeText; }
        public void setResumeText(String v) { resumeText = v; }
        public String getTargetJobTitle() { return targetJobTitle; }
        public void setTargetJobTitle(String v) { targetJobTitle = v; }
        public List<String> getRequiredSkills() { return requiredSkills; }
        public void setRequiredSkills(List<String> v) { requiredSkills = v; }
    }

    public static class ResumeScreenResponse {
        private String candidateName;
        private int matchPercentage;
        private Map<String, String> skillBreakdown; // skill -> MATCH, PARTIAL, MISSING
        private String parsedEducation;
        private String parsedExperience;
        private List<String> parsedSkills;
        private String parsedProjects;
        private String parsedCertifications;
        private String aiExplanation;
        public String getCandidateName() { return candidateName; }
        public void setCandidateName(String v) { candidateName = v; }
        public int getMatchPercentage() { return matchPercentage; }
        public void setMatchPercentage(int v) { matchPercentage = v; }
        public Map<String, String> getSkillBreakdown() { return skillBreakdown; }
        public void setSkillBreakdown(Map<String, String> v) { skillBreakdown = v; }
        public String getParsedEducation() { return parsedEducation; }
        public void setParsedEducation(String v) { parsedEducation = v; }
        public String getParsedExperience() { return parsedExperience; }
        public void setParsedExperience(String v) { parsedExperience = v; }
        public List<String> getParsedSkills() { return parsedSkills; }
        public void setParsedSkills(List<String> v) { parsedSkills = v; }
        public String getParsedProjects() { return parsedProjects; }
        public void setParsedProjects(String v) { parsedProjects = v; }
        public String getParsedCertifications() { return parsedCertifications; }
        public void setParsedCertifications(String v) { parsedCertifications = v; }
        public String getAiExplanation() { return aiExplanation; }
        public void setAiExplanation(String v) { aiExplanation = v; }
    }

    // ── 3. AI Candidate Ranking ─────────────────────────────────
    public static class CandidateRank {
        private Long candidateId;
        private String candidateName;
        private int matchPercentage;
        private String matchedSkills;
        private String missingSkills;
        private String aiAnalysis;
        public Long getCandidateId() { return candidateId; }
        public void setCandidateId(Long v) { candidateId = v; }
        public String getCandidateName() { return candidateName; }
        public void setCandidateName(String v) { candidateName = v; }
        public int getMatchPercentage() { return matchPercentage; }
        public void setMatchPercentage(int v) { matchPercentage = v; }
        public String getMatchedSkills() { return matchedSkills; }
        public void setMatchedSkills(String v) { matchedSkills = v; }
        public String getMissingSkills() { return missingSkills; }
        public void setMissingSkills(String v) { missingSkills = v; }
        public String getAiAnalysis() { return aiAnalysis; }
        public void setAiAnalysis(String v) { aiAnalysis = v; }
        public String getAiExplanation() { return aiAnalysis; }
        public void setAiExplanation(String v) { aiAnalysis = v; }
    }

    // ── 4. AI Job Description Generator ─────────────────────────
    public static class JdGeneratorRequest {
        private String roleTitle;
        private String experience;
        private String skills;
        private String department;
        public String getRoleTitle() { return roleTitle; }
        public void setRoleTitle(String v) { roleTitle = v; }
        public String getExperience() { return experience; }
        public void setExperience(String v) { experience = v; }
        public String getSkills() { return skills; }
        public void setSkills(String v) { skills = v; }
        public String getDepartment() { return department; }
        public void setDepartment(String v) { department = v; }
    }

    public static class JdGeneratorResponse {
        private String roleTitle;
        private String jobSummary;
        private String responsibilities;
        private String requiredSkills;
        private String preferredSkills;
        private String qualifications;
        private String experienceRange;
        public String getRoleTitle() { return roleTitle; }
        public void setRoleTitle(String v) { roleTitle = v; }
        public String getJobSummary() { return jobSummary; }
        public void setJobSummary(String v) { jobSummary = v; }
        public String getResponsibilities() { return responsibilities; }
        public void setResponsibilities(String v) { responsibilities = v; }
        public String getRequiredSkills() { return requiredSkills; }
        public void setRequiredSkills(String v) { requiredSkills = v; }
        public String getPreferredSkills() { return preferredSkills; }
        public void setPreferredSkills(String v) { preferredSkills = v; }
        public String getQualifications() { return qualifications; }
        public void setQualifications(String v) { qualifications = v; }
        public String getExperienceRange() { return experienceRange; }
        public void setExperienceRange(String v) { experienceRange = v; }
    }

    // ── 5. AI Interview Question Generator ──────────────────────
    public static class InterviewQuestionRequest {
        private String role;
        private String round;
        private String experienceYears;
        public String getRole() { return role; }
        public void setRole(String v) { role = v; }
        public String getRound() { return round; }
        public void setRound(String v) { round = v; }
        public String getExperienceYears() { return experienceYears; }
        public void setExperienceYears(String v) { experienceYears = v; }
    }

    public static class InterviewQuestionResponse {
        private String role;
        private String round;
        private Map<String, List<String>> categorizedQuestions; // category -> list of questions
        public String getRole() { return role; }
        public void setRole(String v) { role = v; }
        public String getRound() { return round; }
        public void setRound(String v) { round = v; }
        public Map<String, List<String>> getCategorizedQuestions() { return categorizedQuestions; }
        public void setCategorizedQuestions(Map<String, List<String>> v) { categorizedQuestions = v; }
    }

    // ── 6. AI Interview Feedback Summary ────────────────────────
    public static class FeedbackSummaryRequest {
        private String rawNotes;
        private String candidateName;
        private String role;
        public String getRawNotes() { return rawNotes; }
        public void setRawNotes(String v) { rawNotes = v; }
        public String getCandidateName() { return candidateName; }
        public void setCandidateName(String v) { candidateName = v; }
        public String getRole() { return role; }
        public void setRole(String v) { role = v; }
    }

    public static class FeedbackSummaryResponse {
        private String candidateName;
        private String technicalSkills;
        private String communication;
        private String systemDesign;
        private List<String> strengths;
        private List<String> developmentAreas;
        private String overallRecommendation;
        public String getCandidateName() { return candidateName; }
        public void setCandidateName(String v) { candidateName = v; }
        public String getTechnicalSkills() { return technicalSkills; }
        public void setTechnicalSkills(String v) { technicalSkills = v; }
        public String getCommunication() { return communication; }
        public void setCommunication(String v) { communication = v; }
        public String getSystemDesign() { return systemDesign; }
        public void setSystemDesign(String v) { systemDesign = v; }
        public List<String> getStrengths() { return strengths; }
        public void setStrengths(List<String> v) { strengths = v; }
        public List<String> getDevelopmentAreas() { return developmentAreas; }
        public void setDevelopmentAreas(List<String> v) { developmentAreas = v; }
        public String getOverallRecommendation() { return overallRecommendation; }
        public void setOverallRecommendation(String v) { overallRecommendation = v; }
    }

    // ── 7. AI Performance Summary ───────────────────────────────
    public static class PerformanceSummaryResponse {
        private String employeeName;
        private int goalCompletion;
        private List<String> strengths;
        private List<String> developmentAreas;
        private String recentTrend;
        private String aiSummary;
        public String getEmployeeName() { return employeeName; }
        public void setEmployeeName(String v) { employeeName = v; }
        public int getGoalCompletion() { return goalCompletion; }
        public void setGoalCompletion(int v) { goalCompletion = v; }
        public List<String> getStrengths() { return strengths; }
        public void setStrengths(List<String> v) { strengths = v; }
        public List<String> getDevelopmentAreas() { return developmentAreas; }
        public void setDevelopmentAreas(List<String> v) { developmentAreas = v; }
        public String getRecentTrend() { return recentTrend; }
        public void setRecentTrend(String v) { recentTrend = v; }
        public String getAiSummary() { return aiSummary; }
        public void setAiSummary(String v) { aiSummary = v; }
    }

    // ── 8. AI Skill Gap Analysis ────────────────────────────────
    public static class SkillGapRequest {
        private List<String> currentSkills;
        private String targetRole;
        public List<String> getCurrentSkills() { return currentSkills; }
        public void setCurrentSkills(List<String> v) { currentSkills = v; }
        public String getTargetRole() { return targetRole; }
        public void setTargetRole(String v) { targetRole = v; }
    }

    public static class SkillGapResponse {
        private List<String> strongSkills;
        private List<String> needsDevelopment;
        private List<String> learningPath;
        private String targetRole;
        public List<String> getStrongSkills() { return strongSkills; }
        public void setStrongSkills(List<String> v) { strongSkills = v; }
        public List<String> getNeedsDevelopment() { return needsDevelopment; }
        public void setNeedsDevelopment(List<String> v) { needsDevelopment = v; }
        public List<String> getLearningPath() { return learningPath; }
        public void setLearningPath(List<String> v) { learningPath = v; }
        public String getTargetRole() { return targetRole; }
        public void setTargetRole(String v) { targetRole = v; }
    }

    // ── 9. AI Career Recommendation ─────────────────────────────
    public static class CareerRecommendRequest {
        private String currentDesignation;
        private List<String> currentSkills;
        private String targetRole;
        public String getCurrentDesignation() { return currentDesignation; }
        public void setCurrentDesignation(String v) { currentDesignation = v; }
        public List<String> getCurrentSkills() { return currentSkills; }
        public void setCurrentSkills(List<String> v) { currentSkills = v; }
        public String getTargetRole() { return targetRole; }
        public void setTargetRole(String v) { targetRole = v; }
    }

    public static class CareerRecommendResponse {
        private String targetRole;
        private List<String> suggestedCourses;
        private String readinessLevel;
        private String aiAdvice;
        public String getTargetRole() { return targetRole; }
        public void setTargetRole(String v) { targetRole = v; }
        public List<String> getSuggestedCourses() { return suggestedCourses; }
        public void setSuggestedCourses(List<String> v) { suggestedCourses = v; }
        public String getReadinessLevel() { return readinessLevel; }
        public void setReadinessLevel(String v) { readinessLevel = v; }
        public String getAiAdvice() { return aiAdvice; }
        public void setAiAdvice(String v) { aiAdvice = v; }
    }

    // ── 10. AI Attrition Intelligence ───────────────────────────
    public static class AttritionRiskEntry {
        private String employeeId;
        private String employeeName;
        private String riskLevel;
        private List<String> contributingFactors;
        public String getEmployeeId() { return employeeId; }
        public void setEmployeeId(String v) { employeeId = v; }
        public String getEmployeeName() { return employeeName; }
        public void setEmployeeName(String v) { employeeName = v; }
        public String getRiskLevel() { return riskLevel; }
        public void setRiskLevel(String v) { riskLevel = v; }
        public List<String> getContributingFactors() { return contributingFactors; }
        public void setContributingFactors(List<String> v) { contributingFactors = v; }
    }

    public static class AttritionDashboard {
        private int lowRiskCount;
        private int mediumRiskCount;
        private int highRiskCount;
        private List<AttritionRiskEntry> highRiskEmployees;
        private String disclaimer;
        public int getLowRiskCount() { return lowRiskCount; }
        public void setLowRiskCount(int v) { lowRiskCount = v; }
        public int getMediumRiskCount() { return mediumRiskCount; }
        public void setMediumRiskCount(int v) { mediumRiskCount = v; }
        public int getHighRiskCount() { return highRiskCount; }
        public void setHighRiskCount(int v) { highRiskCount = v; }
        public List<AttritionRiskEntry> getHighRiskEmployees() { return highRiskEmployees; }
        public void setHighRiskEmployees(List<AttritionRiskEntry> v) { highRiskEmployees = v; }
        public String getDisclaimer() { return disclaimer; }
        public void setDisclaimer(String v) { disclaimer = v; }
    }

    // ── 11. AI Workforce Planning ───────────────────────────────
    public static class WorkforcePlanResponse {
        private String roleName;
        private int currentCount;
        private int projectedRequirement;
        private int potentialGap;
        private String analysis;
        public String getRoleName() { return roleName; }
        public void setRoleName(String v) { roleName = v; }
        public int getCurrentCount() { return currentCount; }
        public void setCurrentCount(int v) { currentCount = v; }
        public int getProjectedRequirement() { return projectedRequirement; }
        public void setProjectedRequirement(int v) { projectedRequirement = v; }
        public int getPotentialGap() { return potentialGap; }
        public void setPotentialGap(int v) { potentialGap = v; }
        public String getAnalysis() { return analysis; }
        public void setAnalysis(String v) { analysis = v; }
    }

    // ── 12-13. AI Attendance & Leave Intelligence ────────────────
    public static class AttendanceAnomaly {
        private String department;
        private String insight;
        private List<String> investigationAreas;
        private String severity;
        public String getDepartment() { return department; }
        public void setDepartment(String v) { department = v; }
        public String getInsight() { return insight; }
        public void setInsight(String v) { insight = v; }
        public List<String> getInvestigationAreas() { return investigationAreas; }
        public void setInvestigationAreas(List<String> v) { investigationAreas = v; }
        public String getSeverity() { return severity; }
        public void setSeverity(String v) { severity = v; }
    }

    public static class LeaveInsight {
        private String trendSummary;
        private String highestDepartment;
        private Map<String, Integer> leaveTypeCounts;
        private String recommendation;
        public String getTrendSummary() { return trendSummary; }
        public void setTrendSummary(String v) { trendSummary = v; }
        public String getHighestDepartment() { return highestDepartment; }
        public void setHighestDepartment(String v) { highestDepartment = v; }
        public Map<String, Integer> getLeaveTypeCounts() { return leaveTypeCounts; }
        public void setLeaveTypeCounts(Map<String, Integer> v) { leaveTypeCounts = v; }
        public String getRecommendation() { return recommendation; }
        public void setRecommendation(String v) { recommendation = v; }
    }

    // ── 14. AI Payroll Anomaly Detection ────────────────────────
    public static class PayrollAnomaly {
        private String employeeEmail;
        private double previousNet;
        private double currentNet;
        private double differencePercent;
        private List<String> possibleReasons;
        private String severity;
        public String getEmployeeEmail() { return employeeEmail; }
        public void setEmployeeEmail(String v) { employeeEmail = v; }
        public double getPreviousNet() { return previousNet; }
        public void setPreviousNet(double v) { previousNet = v; }
        public double getCurrentNet() { return currentNet; }
        public void setCurrentNet(double v) { currentNet = v; }
        public double getDifferencePercent() { return differencePercent; }
        public void setDifferencePercent(double v) { differencePercent = v; }
        public List<String> getPossibleReasons() { return possibleReasons; }
        public void setPossibleReasons(List<String> v) { possibleReasons = v; }
        public String getSeverity() { return severity; }
        public void setSeverity(String v) { severity = v; }
    }

    // ── 15. AI Document Intelligence ────────────────────────────
    public static class DocumentOcrRequest {
        private String documentText;
        private String documentType;
        public String getDocumentText() { return documentText; }
        public void setDocumentText(String v) { documentText = v; }
        public String getDocumentType() { return documentType; }
        public void setDocumentType(String v) { documentType = v; }
    }

    public static class DocumentOcrResponse {
        private String extractedName;
        private String extractedInstitution;
        private String extractedDegree;
        private String extractedYear;
        private String verificationStatus;
        private String confidence;
        public String getExtractedName() { return extractedName; }
        public void setExtractedName(String v) { extractedName = v; }
        public String getExtractedInstitution() { return extractedInstitution; }
        public void setExtractedInstitution(String v) { extractedInstitution = v; }
        public String getExtractedDegree() { return extractedDegree; }
        public void setExtractedDegree(String v) { extractedDegree = v; }
        public String getExtractedYear() { return extractedYear; }
        public void setExtractedYear(String v) { extractedYear = v; }
        public String getVerificationStatus() { return verificationStatus; }
        public void setVerificationStatus(String v) { verificationStatus = v; }
        public String getConfidence() { return confidence; }
        public void setConfidence(String v) { confidence = v; }
    }

    // ── 16. AI Dashboard Insights ───────────────────────────────
    public static class RoleDashboardInsight {
        private String role;
        private String greeting;
        private List<String> summaryBullets;
        private String aiRecommendation;
        public String getRole() { return role; }
        public void setRole(String v) { role = v; }
        public String getGreeting() { return greeting; }
        public void setGreeting(String v) { greeting = v; }
        public List<String> getSummaryBullets() { return summaryBullets; }
        public void setSummaryBullets(List<String> v) { summaryBullets = v; }
        public String getAiRecommendation() { return aiRecommendation; }
        public void setAiRecommendation(String v) { aiRecommendation = v; }
    }
}
