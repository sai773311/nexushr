package com.nexushr.auth.ai.controller;

import com.nexushr.auth.ai.model.AiFeatureDtos.*;
import com.nexushr.auth.ai.model.WorkforceInsight;
import com.nexushr.auth.ai.service.WorkforceAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class WorkforceAIController {

    private final WorkforceAIService aiService;

    public WorkforceAIController(WorkforceAIService aiService) {
        this.aiService = aiService;
    }

    // ── Existing CRUD & analytics endpoints ─────────────────────

    @PostMapping("/insights")
    public ResponseEntity<WorkforceInsight> createInsight(@RequestBody WorkforceInsight insight) {
        return ResponseEntity.ok(aiService.createInsight(insight));
    }

    @GetMapping("/insights")
    public ResponseEntity<List<WorkforceInsight>> getAllInsights() {
        return ResponseEntity.ok(aiService.getAllInsights());
    }

    @PostMapping("/analyze")
    public ResponseEntity<WorkforceInsight> analyze(@RequestParam(required = false) String type) {
        return ResponseEntity.ok(aiService.analyze(type));
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> roleInsight(Authentication authentication) {
        String role = authentication.getAuthorities().stream().findFirst()
                .orElseThrow().getAuthority().replace("ROLE_", "");
        return ResponseEntity.ok(aiService.roleInsight(authentication.getName(), role));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<WorkforceInsight>> getEmployeeInsights(@PathVariable Long employeeId) {
        return ResponseEntity.ok(aiService.getEmployeeInsights(employeeId));
    }

    @GetMapping("/type/{insightType}")
    public ResponseEntity<List<WorkforceInsight>> getInsightsByType(@PathVariable String insightType) {
        return ResponseEntity.ok(aiService.getInsightsByType(insightType));
    }

    @GetMapping("/high-risk")
    public ResponseEntity<List<WorkforceInsight>> getHighRiskInsights() {
        return ResponseEntity.ok(aiService.getHighRiskInsights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkforceInsight> getInsightById(@PathVariable Long id) {
        return ResponseEntity.ok(aiService.getInsightById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInsight(@PathVariable Long id) {
        aiService.deleteInsight(id);
        return ResponseEntity.ok("Workforce insight deleted successfully");
    }

    // ══════════════════════════════════════════════════════════════
    //  16 AI FEATURE ENDPOINTS
    // ══════════════════════════════════════════════════════════════

    // ── 1. AI HR Assistant (RBAC-aware) ─────────────────────────
    @PostMapping("/assistant")
    public ResponseEntity<AssistantResponse> assistant(
            @RequestBody AssistantRequest request, Authentication authentication) {
        String role = authentication.getAuthorities().stream().findFirst()
                .orElseThrow().getAuthority().replace("ROLE_", "");
        String email = authentication.getName();
        return ResponseEntity.ok(aiService.assistantChat(request.getQuestion(), email, role));
    }

    // ── 2. AI Resume Screening ──────────────────────────────────
    @PostMapping("/resume/screen")
    public ResponseEntity<ResumeScreenResponse> screenResume(@RequestBody ResumeScreenRequest request) {
        return ResponseEntity.ok(aiService.screenResume(request));
    }

    // ── 3. AI Candidate Ranking ─────────────────────────────────
    @GetMapping("/candidates/rank")
    public ResponseEntity<List<CandidateRank>> rankCandidates(
            @RequestParam(required = false) String jobTitle) {
        return ResponseEntity.ok(aiService.rankCandidates(jobTitle));
    }

    @GetMapping("/candidates/{id}/analysis")
    public ResponseEntity<CandidateRank> candidateAnalysis(@PathVariable Long id) {
        return ResponseEntity.ok(aiService.candidateAnalysis(id));
    }

    // ── 4. AI Job Description Generator ─────────────────────────
    @PostMapping("/jobs/generate-jd")
    public ResponseEntity<JdGeneratorResponse> generateJd(@RequestBody JdGeneratorRequest request) {
        return ResponseEntity.ok(aiService.generateJobDescription(request));
    }

    // ── 5. AI Interview Question Generator ──────────────────────
    @PostMapping("/interview/generate-questions")
    public ResponseEntity<InterviewQuestionResponse> generateQuestions(
            @RequestBody InterviewQuestionRequest request) {
        return ResponseEntity.ok(aiService.generateInterviewQuestions(request));
    }

    // ── 6. AI Interview Feedback Summary ────────────────────────
    @PostMapping("/interview/summarize-feedback")
    public ResponseEntity<FeedbackSummaryResponse> summarizeFeedback(
            @RequestBody FeedbackSummaryRequest request) {
        return ResponseEntity.ok(aiService.summarizeFeedback(request));
    }

    // ── 7. AI Performance Summary ───────────────────────────────
    @GetMapping("/performance/summary/{email}")
    public ResponseEntity<PerformanceSummaryResponse> performanceSummary(@PathVariable String email) {
        return ResponseEntity.ok(aiService.performanceSummary(email));
    }

    // ── 8. AI Skill Gap Analysis ────────────────────────────────
    @PostMapping("/skill-gap/analyze")
    public ResponseEntity<SkillGapResponse> analyzeSkillGap(@RequestBody SkillGapRequest request) {
        return ResponseEntity.ok(aiService.analyzeSkillGap(request));
    }

    // ── 9. AI Career Recommendation ─────────────────────────────
    @PostMapping("/career/recommend")
    public ResponseEntity<CareerRecommendResponse> careerRecommend(
            @RequestBody CareerRecommendRequest request) {
        return ResponseEntity.ok(aiService.careerRecommend(request));
    }

    // ── 10. AI Attrition Intelligence ───────────────────────────
    @GetMapping("/attrition/dashboard")
    public ResponseEntity<AttritionDashboard> attritionDashboard() {
        return ResponseEntity.ok(aiService.attritionDashboard());
    }

    // ── 11. AI Workforce Planning ───────────────────────────────
    @PostMapping("/workforce/plan")
    public ResponseEntity<List<WorkforcePlanResponse>> workforcePlan(
            @RequestParam(required = false) String department) {
        return ResponseEntity.ok(aiService.workforcePlan(department));
    }

    // ── 12. AI Attendance Intelligence ──────────────────────────
    @GetMapping("/attendance/anomalies")
    public ResponseEntity<List<AttendanceAnomaly>> attendanceAnomalies() {
        return ResponseEntity.ok(aiService.attendanceAnomalies());
    }

    // ── 13. AI Leave Intelligence ───────────────────────────────
    @GetMapping("/leave/insights")
    public ResponseEntity<LeaveInsight> leaveInsights() {
        return ResponseEntity.ok(aiService.leaveInsights());
    }

    // ── 14. AI Payroll Anomaly Detection ────────────────────────
    @GetMapping("/payroll/anomalies")
    public ResponseEntity<List<PayrollAnomaly>> payrollAnomalies() {
        return ResponseEntity.ok(aiService.payrollAnomalies());
    }

    // ── 15. AI Document Intelligence ────────────────────────────
    @PostMapping("/document/ocr")
    public ResponseEntity<DocumentOcrResponse> documentOcr(@RequestBody DocumentOcrRequest request) {
        return ResponseEntity.ok(aiService.documentOcr(request));
    }

    // ── 16. AI Dashboard Insights (role-based) ──────────────────
    @GetMapping("/dashboard/insights")
    public ResponseEntity<RoleDashboardInsight> dashboardInsights(Authentication authentication) {
        String role = authentication.getAuthorities().stream().findFirst()
                .orElseThrow().getAuthority().replace("ROLE_", "");
        String email = authentication.getName();
        return ResponseEntity.ok(aiService.dashboardInsights(email, role));
    }
}