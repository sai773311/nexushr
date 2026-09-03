import { StrictMode, useEffect, useState } from 'react'
import { createRoot } from 'react-dom/client'
import './styles.css'

// ── Role-Based Navigation Configuration ──────────────────────
const roleNavigations = {
  EMPLOYEE: [
    ['overview', 'Employee Dashboard', '01'],
    ['ess', 'Employee Self Service (ESS)', '02'],
    ['view_jobs', 'View & Apply Jobs', '03'],
    ['my_attendance', 'My Attendance & Timer', '04'],
    ['my_leaves', 'My Leave Requests', '05'],
    ['my_interviews', 'My Scheduled Interviews', '06'],
    ['my_payslips', 'My Payslips & Salary', '07'],
    ['my_performance', 'My Performance Goals', '08'],
    ['skill_gap', 'Skill Gap & Learning Path', '09'],
    ['my_documents', 'My Uploaded Documents', '10'],
    ['ai_assistant', 'AI HR Assistant', '11']
  ],
  MANAGER: [
    ['overview', 'Manager Dashboard', '01'],
    ['team_people', 'My Team Directory', '02'],
    ['team_attendance', 'Team Attendance', '03'],
    ['leave_approvals', 'Pending Leave Approvals', '04'],
    ['team_performance', 'Team Performance Reviews', '05'],
    ['team_interviews', 'Assigned Interviews', '06'],
    ['performance_ai', 'Team AI Intelligence', '07'],
    ['ai_assistant', 'AI Manager Assistant', '08']
  ],
  RECRUITER: [
    ['overview', 'Recruiter Dashboard', '01'],
    ['job_manager', 'Jobs & AI JD Generator', '02'],
    ['ats_pipeline', 'ATS Recruitment Pipeline', '03'],
    ['resume_screening', 'AI Resume Screening', '04'],
    ['candidate_ranking', 'AI Candidate Ranking', '05'],
    ['interview_scheduler', 'Interview Scheduler', '06'],
    ['interview_tools', 'AI Interview Questions & Feedback', '07'],
    ['ai_assistant', 'AI Recruiter Assistant', '08']
  ],
  HR: [
    ['overview', 'HR Dashboard', '01'],
    ['people', 'Employees', '02'],
    ['departments', 'Departments', '03'],
    ['job_manager', 'Recruitment', '04'],
    ['onboarding', 'Onboarding', '05'],
    ['team_attendance', 'Attendance', '06'],
    ['leave_intelligence', 'Leave Management', '07'],
    ['payroll_engine', 'Payroll', '08'],
    ['team_performance', 'Performance', '09'],
    ['attrition_risk', 'Workforce Intelligence', '10'],
    ['hr_reports', 'Analytics & Reports', '11'],
    ['document_verification', 'Documents', '12'],
    ['assets', 'IT Assets', '13'],
    ['offboarding', 'Offboarding', '14'],
    ['notifications', 'Notifications', '15'],
    ['audit_logs', 'Audit & Activity', '16'],
    ['system_security', 'Security & Settings', '17'],
    ['ai_assistant', 'HR Assistant', '18']
  ],
  ADMIN: [
    ['overview', 'Admin Governance Dashboard', '01'],
    ['user_management', 'User Accounts & Roles', '02'],
    ['permissions_matrix', 'Fine-Grained Permissions Matrix', '03'],
    ['audit_logs', 'Enterprise Audit Logs', '04'],
    ['system_security', 'Security & Login Monitoring', '05'],
    ['departments', 'Organization Setup', '06'],
    ['ai_assistant', 'AI Admin Assistant', '07']
  ]
}

// Initial Shared State
const initialJobs = [
  { id: 1, title: 'Senior Java Backend Developer', department: 'Engineering', location: 'Remote / Bangalore', experience: '3-5 years', salary: '₹18L - ₹24L', status: 'OPEN', description: 'Build scalable microservices with Java 21, Spring Boot, and PostgreSQL.' },
  { id: 2, title: 'React Frontend Engineer', department: 'Engineering', location: 'Bangalore', experience: '2-4 years', salary: '₹14L - ₹18L', status: 'OPEN', description: 'Develop high-performance React UI components with modern CSS design systems.' },
  { id: 3, title: 'DevOps / Kubernetes Specialist', department: 'Infrastructure', location: 'Remote', experience: '4-6 years', salary: '₹22L - ₹28L', status: 'OPEN', description: 'Manage AWS cloud infrastructure, EKS Kubernetes clusters, and CI/CD pipelines.' }
]

const moduleDetails = {
  overview: ['Dashboard', 'Daily priorities, workforce KPIs, and decisions that need attention.', 'Today'],
  people: ['Employees', 'Manage profiles, reporting lines, departments, and employee records.', '620 people'],
  departments: ['Organization', 'Keep departments, designations, and reporting structure accurate.', '12 teams'],
  job_manager: ['Recruitment', 'Create openings, review the hiring pipeline, and move candidates forward.', '3 open roles'],
  onboarding: ['Onboarding', 'Track new starters, documents, equipment, and first-week tasks.', '12 starters'],
  team_attendance: ['Attendance', 'Monitor presence, late arrivals, corrections, and working hours.', 'Today'],
  leave_intelligence: ['Leave Management', 'Review requests, balances, holidays, and team absence patterns.', '2 pending'],
  payroll_engine: ['Payroll', 'Prepare monthly payroll, verify changes, and publish payslips.', 'August ready'],
  team_performance: ['Performance', 'Follow goals, reviews, feedback, and department progress.', '3 reviews due'],
  attrition_risk: ['Workforce Intelligence', 'Spot retention risks and workforce trends before they become urgent.', '25 signals'],
  hr_reports: ['Analytics & Reports', 'Build clear reports for headcount, hiring, leave, payroll, and retention.', '7 reports'],
  document_verification: ['Documents', 'Verify employee documents and keep compliance records complete.', '1 awaiting review'],
  assets: ['IT Assets', 'Track equipment assignment, returns, maintenance, and ownership.', '18 assigned'],
  offboarding: ['Offboarding', 'Coordinate notice periods, clearance, knowledge transfer, and final settlement.', '4 in progress'],
  notifications: ['Notifications', 'Keep employees and managers informed about important people events.', '6 unread'],
  audit_logs: ['Audit & Activity', 'Review who changed what and when for compliance and accountability.', 'Live log'],
  system_security: ['Security & Settings', 'Manage access, sessions, account protection, and organization rules.', 'Healthy'],
  ai_assistant: ['HR Assistant', 'Ask authorized questions and get help with everyday HR work.', 'Ready']
}

const initialCandidates = [
  { id: 101, name: 'Rahul Sharma', email: 'rahul@nexushr.com', jobTitle: 'Senior Java Backend Developer', stage: 'Interview', score: 94, skills: 'Java, Spring Boot, REST API, PostgreSQL', status: 'ACTIVE' },
  { id: 102, name: 'Anita Verma', email: 'anita@gmail.com', jobTitle: 'Senior Java Backend Developer', stage: 'Screening', score: 91, skills: 'Java, Spring Boot, Microservices', status: 'ACTIVE' },
  { id: 103, name: 'Arjun Patel', email: 'arjun@gmail.com', jobTitle: 'React Frontend Engineer', stage: 'Shortlisted', score: 87, skills: 'React, TypeScript, CSS', status: 'ACTIVE' },
  { id: 104, name: 'Priya Nair', email: 'priya.nair@gmail.com', jobTitle: 'Senior Java Backend Developer', stage: 'Applied', score: 83, skills: 'Java, SQL', status: 'ACTIVE' }
]

const initialLeaves = [
  { id: 1, employeeName: 'Rahul Sharma', email: 'rahul@nexushr.com', type: 'Casual Leave', startDate: '2026-09-10', endDate: '2026-09-12', reason: 'Family function in hometown', status: 'PENDING' },
  { id: 2, employeeName: 'Vikram Malhotra', email: 'vikram@nexushr.com', type: 'Sick Leave', startDate: '2026-09-01', endDate: '2026-09-02', reason: 'Viral fever rest', status: 'APPROVED' }
]

const initialAttendance = [
  { id: 1, employeeName: 'Rahul Sharma', email: 'rahul@nexushr.com', date: '2026-09-03', checkIn: '09:08 AM', checkOut: '--', status: 'PRESENT' },
  { id: 2, employeeName: 'Sarah Jenkins', email: 'sarah@nexushr.com', date: '2026-09-03', checkIn: '08:55 AM', checkOut: '--', status: 'PRESENT' },
  { id: 3, employeeName: 'Vikram Malhotra', email: 'vikram@nexushr.com', date: '2026-09-03', checkIn: '--', checkOut: '--', status: 'ABSENT' }
]

const initialDocuments = [
  { id: 1, employeeName: 'Rahul Sharma', docType: 'Degree Certificate', fileName: 'Degree_Certificate_IITD.pdf', institution: 'IIT Delhi', status: 'PENDING_VERIFICATION' },
  { id: 2, employeeName: 'Rahul Sharma', docType: 'PAN Card', fileName: 'PAN_Card_Rahul.pdf', institution: 'Govt of India', status: 'VERIFIED' }
]

const initialAuditLogs = [
  { id: 1, timestamp: '2026-09-03 09:14 AM', actor: 'HR (Priya Nair)', event: 'Created Employee Record EMP1024', status: 'LOGGED' },
  { id: 2, timestamp: '2026-09-03 10:30 AM', actor: 'Manager (Sarah Jenkins)', event: 'Approved Leave Request for EMP1024', status: 'LOGGED' },
  { id: 3, timestamp: '2026-09-03 11:45 AM', actor: 'Recruiter (Alex Mercer)', event: 'Published Job Requisition: Senior Java Backend Developer', status: 'LOGGED' }
]

function App() {
  // Auth Session State
  const [currentUser, setCurrentUser] = useState(JSON.parse(localStorage.getItem('nexushr_user')) || null)

  // Reactive Global Shared Enterprise State
  const [jobs, setJobs] = useState(initialJobs)
  const [candidates, setCandidates] = useState(initialCandidates)
  const [leaves, setLeaves] = useState(initialLeaves)
  const [attendance, setAttendance] = useState(initialAttendance)
  const [documents, setDocuments] = useState(initialDocuments)
  const [auditLogs, setAuditLogs] = useState(initialAuditLogs)

  // Navigation & Drawer State
  const [view, setView] = useState('overview')
  const [aiDrawerOpen, setAiDrawerOpen] = useState(false)
  const [notice, setNotice] = useState('')

  // AI Assistant Chat Messages
  const [chatMessages, setChatMessages] = useState([
    { sender: 'ai', text: 'Hello! I am your NexusHR AI Assistant. How can I assist your workflow today?' }
  ])
  const [chatInput, setChatInput] = useState('')

  const handleLogin = (userObj) => {
    setCurrentUser(userObj)
    localStorage.setItem('nexushr_user', JSON.stringify(userObj))
    localStorage.setItem('nexushr_role', userObj.role)
    setView('overview')
    setNotice(`Successfully signed in as ${userObj.name} (${userObj.role} Role).`)
    logEvent(userObj.name, `User logged in to session as ${userObj.role}`)
  }

  const handleLogout = () => {
    if (currentUser) {
      logEvent(currentUser.name, `User logged out of session`)
    }
    setCurrentUser(null)
    localStorage.removeItem('nexushr_user')
    localStorage.removeItem('nexushr_role')
  }

  const logEvent = (actor, eventMsg) => {
    const newLog = {
      id: Date.now(),
      timestamp: new Date().toLocaleString(),
      actor: actor,
      event: eventMsg,
      status: 'LOGGED'
    }
    setAuditLogs((prev) => [newLog, ...prev])
  }

  const handleChatSubmit = (e) => {
    if (e) e.preventDefault()
    if (!chatInput.trim()) return

    const userText = chatInput
    setChatMessages((prev) => [...prev, { sender: 'user', text: userText }])
    setChatInput('')

    const q = userText.toLowerCase()
    const role = currentUser?.role || 'EMPLOYEE'

    // Security RBAC Guard Test
    if (role === 'EMPLOYEE' && (q.includes('salary') || q.includes('payslip') || q.includes('pay')) && (q.includes('rahul') || q.includes('anita') || q.includes('other') || q.includes('everyone'))) {
      setTimeout(() => {
        setChatMessages((prev) => [
          ...prev,
          {
            sender: 'ai',
            isDenied: true,
            text: 'ACCESS DENIED: As an Employee, you are unauthorized to view private salary or confidential records of other employees.'
          }
        ])
      }, 300)
      return
    }

    setTimeout(() => {
      let reply = 'Here is the authorized information based on your active role:'
      if (q.includes('leave balance') || q.includes('leave')) {
        reply = `Your authorized leave balance is 12 annual leave days remaining. You have ${leaves.filter(l => l.email === currentUser?.email && l.status === 'PENDING').length} pending leave requests.`
      } else if (q.includes('interview')) {
        reply = 'Next scheduled interview: Senior Java Backend Developer round tomorrow at 10:00 AM. 12 candidates are currently in technical rounds.'
      } else if (q.includes('attendance') || q.includes('absent')) {
        reply = role === 'EMPLOYEE' ? 'Your attendance this month: 21 Days Present, 0 Absences, 1 WFH day.' : `Workforce Attendance Today: ${attendance.filter(a => a.status === 'PRESENT').length} Present, ${attendance.filter(a => a.status === 'ABSENT').length} Absent.`
      } else if (q.includes('payslip') || q.includes('salary')) {
        reply = 'Your latest August 2026 Payslip: Gross ₹71,500 | Net Payable ₹65,900 after deductions.'
      } else if (q.includes('goals') || q.includes('performance')) {
        reply = 'Assigned Goals: 1) Complete 10 REST APIs (87%), 2) Cloud Certification (60%). Overall Completion: 87%.'
      } else if (q.includes('hiring') || q.includes('job')) {
        reply = `Recruitment Pipeline: ${jobs.length} active job requisitions published. ${candidates.length} candidates in ATS pipeline.`
      }
      setChatMessages((prev) => [...prev, { sender: 'ai', text: reply }])
    }, 300)
  }

  // ── Show ONLY Sign-In Screen when not logged in ───────────
  if (!currentUser) {
    return <SinglePageAuthScreen onLogin={handleLogin} />
  }

  const roleNav = roleNavigations[currentUser.role] || roleNavigations.EMPLOYEE

  return (
    <div className="app-shell">
      {/* ── Sidebar Navigation ────────────────────────────── */}
      <aside>
        <div className="brand-mark">
          <span>N</span> NexusHR
        </div>

        {/* User Account Profile Header */}
        <div className="role-switcher-banner">
          <label>ACTIVE SESSION</label>
          <strong style={{ color: '#38bdf8', fontSize: '13px', display: 'block' }}>{currentUser.name}</strong>
          <span className={`role-badge session-role-${currentUser.role.toLowerCase()}`} style={{ marginTop: '4px', display: 'inline-block' }}>{currentUser.role}</span>
        </div>

        <nav>
          {roleNav.map(([id, label, number]) => (
            <button
              key={id}
              className={view === id ? 'active' : ''}
              onClick={() => setView(id)}
            >
              <b>{number}</b>
              {label}
            </button>
          ))}
        </nav>

        <div className="sidebar-bottom">
          <button className="ai-assistant-launch" onClick={() => setAiDrawerOpen(true)}>
            <span>✦</span> Launch AI Assistant
          </button>

          <button
            onClick={handleLogout}
            style={{ width: '100%', background: '#334155', color: '#f8fafc', padding: '10px', borderRadius: '6px', marginTop: '10px', fontSize: '12px', fontWeight: 'bold' }}
          >
            Sign Out 🚪
          </button>
        </div>
      </aside>

      {/* ── Main Workspace Content Area ──────────────────── */}
      <main className="content">
        <header>
          <div>
            <p className="eyebrow">{new Date().toLocaleDateString(undefined, { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' })}</p>
            <h1 className={`platform-title platform-title-${currentUser.role.toLowerCase()}`} style={{ fontSize: '22px', margin: '4px 0' }}>NexusHR Enterprise Platform</h1>
          </div>

          <div className="header-user-info">
            <span className={`role-badge session-role-${currentUser.role.toLowerCase()}`}>{currentUser.role} SESSION</span>
            <button className="btn-icon" onClick={() => setAiDrawerOpen(true)}>
              <span>🤖</span> AI Assistant
            </button>
          </div>
        </header>

        {notice && (
          <div className="notice-banner">
            <span>{notice}</span>
            <button onClick={() => setNotice('')} style={{ background: 'transparent', fontWeight: 'bold' }}>✕</button>
          </div>
        )}

        {/* Dynamic View Render Based on Logged In Role & Selection */}
        {view === 'overview' && <OverviewModule user={currentUser} jobs={jobs} candidates={candidates} leaves={leaves} attendance={attendance} onNavigate={setView} setAiDrawerOpen={setAiDrawerOpen} />}
        {view === 'ess' && <EmployeeSelfServiceModule user={currentUser} leaves={leaves} attendance={attendance} documents={documents} />}
        {view === 'view_jobs' && <ViewJobsModule jobs={jobs} user={currentUser} setCandidates={setCandidates} setNotice={setNotice} logEvent={logEvent} />}
        {view === 'my_attendance' && <MyAttendanceModule user={currentUser} attendance={attendance} setAttendance={setAttendance} setNotice={setNotice} logEvent={logEvent} />}
        {view === 'my_leaves' && <MyLeavesModule user={currentUser} leaves={leaves} setLeaves={setLeaves} setNotice={setNotice} logEvent={logEvent} />}
        {view === 'my_interviews' && <MyInterviewsModule user={currentUser} />}
        {view === 'my_payslips' && <MyPayslipsModule user={currentUser} />}
        {view === 'my_performance' && <MyPerformanceModule user={currentUser} />}
        {view === 'skill_gap' && <SkillGapModule />}
        {view === 'my_documents' && <MyDocumentsModule user={currentUser} documents={documents} setDocuments={setDocuments} setNotice={setNotice} logEvent={logEvent} />}

        {/* Manager Views */}
        {view === 'team_people' && <TeamPeopleModule />}
        {view === 'team_attendance' && <TeamAttendanceModule attendance={attendance} />}
        {view === 'leave_approvals' && <LeaveApprovalsModule leaves={leaves} setLeaves={setLeaves} user={currentUser} setNotice={setNotice} logEvent={logEvent} />}
        {view === 'team_performance' && <TeamPerformanceModule />}
        {view === 'team_interviews' && <TeamInterviewsModule />}

        {/* Recruiter Views */}
        {view === 'job_manager' && <JobManagerModule jobs={jobs} setJobs={setJobs} user={currentUser} setNotice={setNotice} logEvent={logEvent} />}
        {view === 'ats_pipeline' && <AtsPipelineModule candidates={candidates} setCandidates={setCandidates} logEvent={logEvent} />}
        {view === 'resume_screening' && <ResumeScreeningModule />}
        {view === 'candidate_ranking' && <CandidateRankingModule candidates={candidates} />}
        {view === 'interview_scheduler' && <InterviewSchedulerModule candidates={candidates} setNotice={setNotice} logEvent={logEvent} />}
        {view === 'interview_tools' && <InterviewToolsModule />}

        {/* HR Views */}
        {view === 'people' && <EmployeeLifecycleModule />}
        {view === 'departments' && <DepartmentsModule />}
        {view === 'onboarding' && <HrOperationsModule title="Onboarding & Offboarding" eyebrow="PEOPLE MOVEMENT" description="Coordinate every joiner, mover, and leaver with clear ownership and due dates." metrics={['12 new starters', '4 exit checklists', '96% completion']} actions={['Create onboarding plan', 'Assign equipment & access', 'Review exit checklist']} />}
        {view === 'benefits' && <HrOperationsModule title="Benefits & Time Off" eyebrow="EMPLOYEE EXPERIENCE" description="Manage benefit enrollment, leave policies, and employee questions from one workspace." metrics={['98% enrolled', '2 pending changes', '12 leave policies']} actions={['Open enrollment window', 'Review leave policy', 'Export benefit roster']} />}
        {view === 'compliance' && <HrOperationsModule title="Compliance & Policies" eyebrow="RISK CONTROL" description="Keep contracts, policies, training, and statutory tasks visible before deadlines arrive." metrics={['14 tasks due', '620 policy members', '0 critical gaps']} actions={['Review policy acknowledgements', 'Schedule compliance training', 'Run compliance audit']} />}
        {view === 'employee_relations' && <HrOperationsModule title="Employee Relations" eyebrow="CASE MANAGEMENT" description="Track confidential cases, investigations, and follow-ups with a consistent resolution workflow." metrics={['6 open cases', '2 follow-ups today', '4.8d avg. resolution']} actions={['Open case intake', 'Log conversation', 'Review escalations']} />}
        {view === 'engagement' && <HrOperationsModule title="Engagement & Surveys" eyebrow="LISTEN TO YOUR PEOPLE" description="Turn pulse feedback into visible actions for teams, managers, and leadership." metrics={['84% response rate', '3 active pulses', '+11 eNPS change']} actions={['Launch pulse survey', 'Review team insights', 'Create action plan']} />}
        {view === 'workforce_plan' && <WorkforcePlanningModule jobs={jobs} />}
        {view === 'leave_intelligence' && <LeaveIntelligenceModule leaves={leaves} />}
        {view === 'payroll_engine' && <PayrollEngineModule />}
        {view === 'payroll_anomalies' && <PayrollAnomaliesModule />}
        {view === 'document_verification' && <DocumentVerificationModule documents={documents} setDocuments={setDocuments} setNotice={setNotice} logEvent={logEvent} />}
        {view === 'assets' && <SimpleOperationsModule title="IT Assets" eyebrow="EQUIPMENT REGISTER" description="Keep every laptop, monitor, and access item assigned, returned, and accounted for." metrics={['18 assigned', '6 available', '2 maintenance']} actions={['Assign equipment', 'Record asset return', 'Open maintenance queue']} />}
        {view === 'offboarding' && <SimpleOperationsModule title="Offboarding" eyebrow="EXIT MANAGEMENT" description="Coordinate notice periods, knowledge transfer, asset returns, and final clearance." metrics={['4 in progress', '2 awaiting return', '0 overdue']} actions={['Start exit checklist', 'Review clearance', 'Prepare final settlement']} />}
        {view === 'notifications' && <SimpleOperationsModule title="Notifications" eyebrow="PEOPLE COMMUNICATIONS" description="Review important updates and keep employees, managers, and HR aligned." metrics={['6 unread', '14 sent today', '0 failed']} actions={['Compose announcement', 'Review delivery log', 'Manage notification rules']} />}
        {view === 'attrition_risk' && <AttritionRiskModule />}
        {view === 'hr_reports' && <HrOperationsModule title="People Analytics & Reports" eyebrow="DECISION SUPPORT" description="Build a clear operating picture across headcount, hiring, absence, payroll, and retention." metrics={['620 employees', '18 hires this month', '7 live reports']} actions={['Build workforce report', 'Export headcount data', 'Schedule leadership digest']} />}

        {/* Admin Views */}
        {view === 'user_management' && <UserManagementModule onSwitchUser={handleLogin} />}
        {view === 'permissions_matrix' && <PermissionsMatrixModule />}
        {view === 'audit_logs' && <AuditLogsModule logs={auditLogs} />}
        {view === 'system_security' && <SystemSecurityModule />}

        {/* AI Assistant View */}
        {view === 'ai_assistant' && <AiAssistantView user={currentUser} onAsk={(q) => { setChatInput(q); setAiDrawerOpen(true); }} />}
      </main>

      {/* ── AI Assistant Drawer Modal ───────────────────────── */}
      {aiDrawerOpen && (
        <div className="ai-modal-overlay" onClick={() => setAiDrawerOpen(false)}>
          <div className="ai-modal-drawer" onClick={(e) => e.stopPropagation()}>
            <div className="ai-drawer-header">
              <h3>🤖 AI Assistant ({currentUser.role})</h3>
              <button onClick={() => setAiDrawerOpen(false)} style={{ color: '#fff', background: 'transparent', fontSize: '18px' }}>✕</button>
            </div>

            <div className="ai-chat-body">
              {chatMessages.map((msg, index) => (
                <div key={index} className={`chat-bubble ${msg.sender} ${msg.isDenied ? 'denied' : ''}`}>
                  {msg.text}
                </div>
              ))}

              <div style={{ marginTop: 'auto', paddingTop: '10px' }}>
                <p className="eyebrow" style={{ marginBottom: '6px' }}>Suggested Prompts ({currentUser.role}):</p>
                <div className="chip-group">
                  {currentUser.role === 'EMPLOYEE' && (
                    <>
                      <button className="chip-btn" onClick={() => { setChatInput('What is my leave balance?'); }}>What is my leave balance?</button>
                      <button className="chip-btn" onClick={() => { setChatInput('When is my next interview?'); }}>When is my next interview?</button>
                      <button className="chip-btn" onClick={() => { setChatInput('Show my latest payslip.'); }}>Show my latest payslip</button>
                      <button className="chip-btn" onClick={() => { setChatInput("Show Rahul's salary."); }}>Show Rahul's salary (RBAC Guard Test)</button>
                    </>
                  )}
                  {currentUser.role === 'MANAGER' && (
                    <>
                      <button className="chip-btn" onClick={() => { setChatInput('Who is absent today?'); }}>Who is absent today?</button>
                      <button className="chip-btn" onClick={() => { setChatInput('Show my pending approvals.'); }}>Show pending approvals</button>
                    </>
                  )}
                  {currentUser.role === 'HR' && (
                    <>
                      <button className="chip-btn" onClick={() => { setChatInput('How many employees joined this month?'); }}>Joined this month?</button>
                      <button className="chip-btn" onClick={() => { setChatInput('Which department has highest hiring requirement?'); }}>Highest hiring department</button>
                    </>
                  )}
                  {currentUser.role === 'RECRUITER' && (
                    <>
                      <button className="chip-btn" onClick={() => { setChatInput('How many candidates are in technical interview?'); }}>Candidates in interview?</button>
                    </>
                  )}
                </div>
              </div>
            </div>

            <form className="ai-input-bar" onSubmit={handleChatSubmit}>
              <input
                type="text"
                placeholder="Ask AI Assistant..."
                value={chatInput}
                onChange={(e) => setChatInput(e.target.value)}
              />
              <button type="submit">Send</button>
            </form>
          </div>
        </div>
      )}
    </div>
  )
}

// ══════════════════════════════════════════════════════════════
// DEDICATED SINGLE PAGE SIGN-IN / SIGN-UP COMPONENT
// ══════════════════════════════════════════════════════════════
function SinglePageAuthScreen({ onLogin }) {
  const [isRegister, setIsRegister] = useState(false)
  const [email, setEmail] = useState('rahul@nexushr.com')
  const [password, setPassword] = useState('password')
  const [role, setRole] = useState('EMPLOYEE')
  const [fullName, setFullName] = useState('Rahul Sharma')

  const demoAccounts = [
    { name: 'Rahul Sharma', email: 'rahul@nexushr.com', role: 'EMPLOYEE', title: 'Software Developer' },
    { name: 'Sarah Jenkins', email: 'sarah@nexushr.com', role: 'MANAGER', title: 'Engineering Manager' },
    { name: 'Alex Mercer', email: 'alex@nexushr.com', role: 'RECRUITER', title: 'Senior Recruiter' },
    { name: 'Priya Nair', email: 'priya@nexushr.com', role: 'HR', title: 'HR Operations Lead' },
    { name: 'Super Admin', email: 'admin@nexushr.com', role: 'ADMIN', title: 'System Administrator' }
  ]

  const handleSubmit = (e) => {
    e.preventDefault()
    onLogin({
      name: isRegister ? fullName : email.split('@')[0].toUpperCase(),
      email,
      role
    })
  }

  return (
    <div className="auth-screen" style={{ minHeight: '100vh', display: 'grid', gridTemplateColumns: '1.1fr 0.9fr', background: '#0f172a' }}>
      {/* Left Artwork Column */}
      <div className="auth-art" style={{ padding: '80px', color: '#fff', display: 'flex', flexDirection: 'column', justifyContent: 'center', background: 'linear-gradient(135deg, #0f172a 0%, #0f766e 100%)' }}>
        <div className="brand-mark" style={{ fontSize: '32px', marginBottom: '24px' }}>
          <span>N</span> NexusHR
        </div>
        <p className="eyebrow" style={{ color: '#5eead4' }}>NEXUS / ENTERPRISE PEOPLE OS</p>
        <h1 style={{ fontSize: '54px', lineHeight: '1.05', color: '#fff', margin: '16px 0 24px' }}>
          Make work feel<br /><em style={{ color: '#99f6e4', fontStyle: 'italic' }}>human & intelligent.</em>
        </h1>
        <p style={{ color: '#94a3b8', fontSize: '15px', maxWidth: '440px', lineHeight: '1.6', marginBottom: '40px' }}>
          One unified command center for enterprise HR operations, ATS recruitment pipelines, payroll, and RBAC-governed workforce AI.
        </p>

        <div style={{ font: '11px DM Mono', color: '#5eead4', letterSpacing: '0.08em' }}>
          ✦ 20 INNER MODULES CONNECTED · 16 AI WORKFLOWS ACTIVE
        </div>
      </div>

      {/* Right Sign In / Sign Up Form Column */}
      <div className="auth-form" style={{ background: '#fff', padding: '70px 60px', display: 'flex', flexDirection: 'column', justifyContent: 'center' }}>
        <div style={{ marginBottom: '28px' }}>
          <p className="eyebrow">ENTERPRISE ACCESS</p>
          <h2 style={{ fontSize: '30px', margin: '6px 0 8px' }}>{isRegister ? 'Create Your Account' : 'Sign In to NexusHR'}</h2>
          <p style={{ color: 'var(--muted)', fontSize: '13px' }}>
            {isRegister ? 'Register your employee or manager account.' : 'Enter your credentials and select your authorized role.'}
          </p>
        </div>

        {/* Credentials Form */}
        <form onSubmit={handleSubmit} style={{ display: 'grid', gap: '14px', marginBottom: '28px' }}>
          {isRegister && (
            <div>
              <label style={{ font: '11px DM Mono', textTransform: 'uppercase', color: 'var(--muted)' }}>Full Name</label>
              <input
                type="text"
                required
                style={{ width: '100%', padding: '12px', border: '1px solid var(--line)', borderRadius: '8px', marginTop: '4px' }}
                value={fullName}
                onChange={(e) => setFullName(e.target.value)}
                placeholder="Avery Morgan"
              />
            </div>
          )}

          <div>
            <label style={{ font: '11px DM Mono', textTransform: 'uppercase', color: 'var(--muted)' }}>Work Email</label>
            <input
              type="email"
              required
              style={{ width: '100%', padding: '12px', border: '1px solid var(--line)', borderRadius: '8px', marginTop: '4px' }}
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="you@company.com"
            />
          </div>

          <div>
            <label style={{ font: '11px DM Mono', textTransform: 'uppercase', color: 'var(--muted)' }}>Password</label>
            <input
              type="password"
              required
              style={{ width: '100%', padding: '12px', border: '1px solid var(--line)', borderRadius: '8px', marginTop: '4px' }}
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="••••••••"
            />
          </div>

          <div>
            <label style={{ font: '11px DM Mono', textTransform: 'uppercase', color: 'var(--muted)' }}>Select Account Role</label>
            <select
              style={{ width: '100%', padding: '12px', border: '1px solid var(--line)', borderRadius: '8px', marginTop: '4px', fontWeight: 'bold', color: 'var(--primary)' }}
              value={role}
              onChange={(e) => setRole(e.target.value)}
            >
              <option value="EMPLOYEE">EMPLOYEE (Rahul - Software Developer)</option>
              <option value="MANAGER">MANAGER (Sarah - Engineering Lead)</option>
              <option value="RECRUITER">RECRUITER (Alex - Talent Acquisition)</option>
              <option value="HR">HR (Priya - Operations Lead)</option>
              <option value="ADMIN">ADMIN (Super Admin)</option>
            </select>
          </div>

          <button className="btn-primary" type="submit" style={{ width: '100%', padding: '14px', fontSize: '14px', marginTop: '6px' }}>
            {isRegister ? 'Create Account ➔' : 'Sign In to Nexus ➔'}
          </button>
        </form>

        <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '24px' }}>
          <button
            onClick={() => setIsRegister(!isRegister)}
            style={{ background: 'transparent', color: 'var(--muted)', textDecoration: 'underline', fontSize: '12px' }}
          >
            {isRegister ? 'Already have an account? Sign in' : 'Need a new account? Create one'}
          </button>
        </div>

        {/* Quick Demo Login Preset Buttons */}
        <div style={{ borderTop: '1px solid var(--line)', paddingTop: '20px' }}>
          <p className="eyebrow" style={{ marginBottom: '10px' }}>One-Click Demo Role Accounts:</p>
          <div style={{ display: 'flex', gap: '6px', flexWrap: 'wrap' }}>
            {demoAccounts.map((acc) => (
              <button
                key={acc.email}
                className="chip-btn"
                style={{ padding: '6px 12px', fontWeight: 'bold' }}
                onClick={() => onLogin(acc)}
              >
                Sign in as {acc.role} ({acc.name.split(' ')[0]})
              </button>
            ))}
          </div>
        </div>
      </div>
    </div>
  )
}

// ══════════════════════════════════════════════════════════════
// MODULE COMPONENTS
// ══════════════════════════════════════════════════════════════

// 1. Dashboard Overview
function OverviewModule({ user, jobs, candidates, leaves, attendance, onNavigate, setAiDrawerOpen }) {
  return (
    <div className="view-stack">
      <div className="welcome-card">
        <div>
          <p className="eyebrow" style={{ color: '#5eead4' }}>{user.role} DASHBOARD SESSION</p>
          <h2>Welcome back, {user.name}!</h2>
          <p>Real-time system data and role-customized AI intelligence.</p>
        </div>
        <button className="btn-primary" onClick={() => setAiDrawerOpen(true)}>Launch AI Assistant ✦</button>
      </div>

      {/* Role-tailored Dashboard AI Insights Card */}
      <div className="panel" style={{ background: 'linear-gradient(135deg, #f0fdf4 0%, #e0f2fe 100%)' }}>
        <div className="panel-header">
          <h3>🤖 AI {user.role} Insights Summary</h3>
          <span className="status-chip success">LIVE AI INSIGHT</span>
        </div>
        <ul style={{ paddingLeft: '20px', lineHeight: '1.8', fontSize: '13px' }}>
          {user.role === 'EMPLOYEE' && (
            <>
              <li>Authorized Leave Balance: <strong>12 Days</strong> remaining.</li>
              <li>Upcoming Interview: Senior Java Backend Developer round tomorrow at 10:00 AM.</li>
              <li>Goal Progress: Backend REST API completion at 87%.</li>
            </>
          )}
          {user.role === 'MANAGER' && (
            <>
              <li>{leaves.filter(l => l.status === 'PENDING').length} pending leave requests require your approval attention.</li>
              <li>Team Attendance today: {attendance.filter(a => a.status === 'PRESENT').length} Present, {attendance.filter(a => a.status === 'ABSENT').length} Absent.</li>
              <li>3 direct reports have quarterly appraisals due.</li>
            </>
          )}
          {user.role === 'RECRUITER' && (
            <>
              <li>{jobs.length} active job requisitions published.</li>
              <li>{candidates.length} candidates in ATS recruitment pipeline.</li>
              <li>8 resumes awaiting AI screening analysis.</li>
            </>
          )}
          {user.role === 'HR' && (
            <>
              <li>18 new employees joined this month.</li>
              <li>Engineering department has the highest hiring gap (6 requisitions).</li>
              <li>1 document awaiting OCR HR verification.</li>
            </>
          )}
          {user.role === 'ADMIN' && (
            <>
              <li>620 active user accounts across the enterprise.</li>
              <li>17 failed-login events logged for security audit review.</li>
              <li>System Health: All microservices operational (99.99% uptime).</li>
            </>
          )}
        </ul>
      </div>

      <div className="metric-grid">
        <div className="metric-card">
          <p>ACTIVE JOBS</p>
          <strong>{jobs.length}</strong>
          <span className="metric-tag">View Open Requisitions</span>
        </div>
        <div className="metric-card">
          <p>ATS CANDIDATES</p>
          <strong>{candidates.length}</strong>
          <span className="metric-tag">In Hiring Pipeline</span>
        </div>
        <div className="metric-card">
          <p>PENDING LEAVES</p>
          <strong>{leaves.filter(l => l.status === 'PENDING').length}</strong>
          <span className="metric-tag">Awaiting Approval</span>
        </div>
        <div className="metric-card">
          <p>PRESENT TODAY</p>
          <strong>{attendance.filter(a => a.status === 'PRESENT').length}</strong>
          <span className="metric-tag">On-time Attendance</span>
        </div>
      </div>

      <div className="module-directory">
        <div className="panel-header">
          <div>
            <p className="eyebrow">WORKSPACE MODULES</p>
            <h3>What do you need to work on?</h3>
          </div>
          <span className="status-chip info">{roleNavigations[user.role].length - 1} AVAILABLE</span>
        </div>
        <div className="module-grid">
          {roleNavigations[user.role].slice(1).map(([id, label, number]) => {
            const detail = moduleDetails[id] || [label, 'Open this workspace to review and manage the latest activity.', 'Ready']
            return (
              <button className="module-card" key={id} onClick={() => onNavigate(id)}>
                <span className="module-number">{number}</span>
                <span className="module-icon">{['people', 'departments', 'job_manager', 'onboarding'].includes(id) ? '◈' : ['payroll_engine', 'my_payslips'].includes(id) ? '◌' : '＋'}</span>
                <strong>{detail[0]}</strong>
                <small>{detail[1]}</small>
                <em>{detail[2]} <span>↗</span></em>
              </button>
            )
          })}
        </div>
      </div>
    </div>
  )
}

// 2. Employee Self Service (ESS)
function EmployeeSelfServiceModule({ user, leaves, attendance, documents }) {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>👤 Employee Self Service Portal ({user.name})</h3>
        </div>

        <div className="metric-grid" style={{ marginBottom: '20px' }}>
          <div className="metric-card">
            <p>MY LEAVE BALANCE</p>
            <strong style={{ color: 'var(--primary)' }}>12 Days</strong>
            <span className="metric-tag">Annual Leave</span>
          </div>
          <div className="metric-card">
            <p>ATTENDANCE THIS MONTH</p>
            <strong style={{ color: 'var(--success)' }}>21 Days</strong>
            <span className="metric-tag">100% On-time</span>
          </div>
          <div className="metric-card">
            <p>MY GOAL PROGRESS</p>
            <strong style={{ color: 'var(--primary)' }}>87%</strong>
            <span className="metric-tag">On Track</span>
          </div>
        </div>

        <h4 style={{ margin: '16px 0 8px' }}>My Active Applications & Requests</h4>
        <ul>
          {leaves.filter(l => l.email === user.email).map((l) => (
            <li key={l.id} style={{ marginBottom: '6px' }}>
              Leave Request ({l.type}): <strong>{l.startDate} to {l.endDate}</strong> ➔ <span className="status-chip warning">{l.status}</span>
            </li>
          ))}
        </ul>
      </div>
    </div>
  )
}

// 3. View Jobs & Apply (Employee Role)
function ViewJobsModule({ jobs, user, setCandidates, setNotice, logEvent }) {
  const [appliedJobId, setAppliedJobId] = useState(null)

  const handleApply = (job) => {
    const newCandidate = {
      id: Date.now(),
      name: user.name,
      email: user.email,
      jobTitle: job.title,
      stage: 'Applied',
      score: 88,
      skills: 'Java, React, SQL',
      status: 'ACTIVE'
    }
    setCandidates((prev) => [newCandidate, ...prev])
    setAppliedJobId(job.id)
    setNotice(`Successfully submitted your application for ${job.title}! Your application is now active in the ATS pipeline.`)
    logEvent(user.name, `Applied for job: ${job.title}`)
  }

  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>💼 Open Job Opportunities ({jobs.length} Active Positions)</h3>
        </div>

        <div style={{ display: 'grid', gap: '16px' }}>
          {jobs.map((job) => (
            <div key={job.id} style={{ border: '1px solid var(--line)', borderRadius: '10px', padding: '18px', background: 'var(--bg-subtle)' }}>
              <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                <h4 style={{ margin: 0, fontSize: '16px', color: 'var(--primary)' }}>{job.title}</h4>
                <span className="status-chip info">{job.salary}</span>
              </div>
              <p style={{ fontSize: '12px', color: 'var(--muted)', margin: '6px 0 12px' }}>
                Department: <strong>{job.department}</strong> · Location: <strong>{job.location}</strong> · Experience: <strong>{job.experience}</strong>
              </p>
              <p style={{ fontSize: '13px', lineHeight: '1.5' }}>{job.description}</p>

              {appliedJobId === job.id ? (
                <span className="status-chip success" style={{ marginTop: '10px' }}>✓ APPLICATION SUBMITTED</span>
              ) : (
                <button className="btn-primary" onClick={() => handleApply(job)} style={{ marginTop: '10px' }}>
                  Apply Now ↗
                </button>
              )}
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}

// 4. My Attendance & Check-In Timer (Employee Role)
function MyAttendanceModule({ user, attendance, setAttendance, setNotice, logEvent }) {
  const [isCheckedIn, setIsCheckedIn] = useState(true)

  const handleToggleCheckIn = () => {
    if (!isCheckedIn) {
      const newRecord = {
        id: Date.now(),
        employeeName: user.name,
        email: user.email,
        date: new Date().toISOString().split('T')[0],
        checkIn: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
        checkOut: '--',
        status: 'PRESENT'
      }
      setAttendance((prev) => [newRecord, ...prev])
      setIsCheckedIn(true)
      setNotice('Checked in successfully at ' + newRecord.checkIn)
      logEvent(user.name, 'Checked in for work shift')
    } else {
      setIsCheckedIn(false)
      setNotice('Checked out successfully.')
      logEvent(user.name, 'Checked out of work shift')
    }
  }

  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>⏱️ Attendance Desk & Shift Timer ({user.name})</h3>
        </div>

        <div style={{ textAlign: 'center', padding: '30px', background: 'var(--bg-subtle)', borderRadius: '12px' }}>
          <p className="eyebrow">SHIFT STATUS TODAY</p>
          <h2 style={{ fontSize: '38px', color: 'var(--primary)', margin: '10px 0' }}>Working Hours: 8h 42m</h2>
          <p>Status: <span className="status-chip success">{isCheckedIn ? 'CHECKED IN (WORKING)' : 'CHECKED OUT'}</span></p>

          <button
            className="btn-primary"
            style={{ marginTop: '20px', background: isCheckedIn ? '#242424' : 'var(--primary)', padding: '14px 28px', fontSize: '15px' }}
            onClick={handleToggleCheckIn}
          >
            {isCheckedIn ? 'CHECK OUT ⏹' : 'CHECK IN ▶'}
          </button>
        </div>
      </div>
    </div>
  )
}

// 5. My Leave Requests (Employee Role)
function MyLeavesModule({ user, leaves, setLeaves, setNotice, logEvent }) {
  const [type, setType] = useState('Casual Leave')
  const [startDate, setStartDate] = useState('2026-09-15')
  const [endDate, setEndDate] = useState('2026-09-17')
  const [reason, setReason] = useState('Personal work')

  const handleApplyLeave = (e) => {
    e.preventDefault()
    const newLeave = {
      id: Date.now(),
      employeeName: user.name,
      email: user.email,
      type: type,
      startDate: startDate,
      endDate: endDate,
      reason: reason,
      status: 'PENDING'
    }
    setLeaves((prev) => [newLeave, ...prev])
    setNotice(`Leave request (${type}) submitted for Manager approval.`)
    logEvent(user.name, `Applied for leave: ${type} (${startDate} to ${endDate})`)
  }

  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>📅 Leave Requests & Application</h3>
        </div>

        <form onSubmit={handleApplyLeave} style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: '12px', marginBottom: '24px', background: 'var(--bg-subtle)', padding: '16px', borderRadius: '10px' }}>
          <div>
            <label style={{ font: '11px DM Mono', textTransform: 'uppercase' }}>Leave Type</label>
            <select style={{ width: '100%', padding: '8px', border: '1px solid var(--line)', borderRadius: '6px' }} value={type} onChange={(e) => setType(e.target.value)}>
              <option>Casual Leave</option>
              <option>Sick Leave</option>
              <option>Earned Leave</option>
            </select>
          </div>
          <div>
            <label style={{ font: '11px DM Mono', textTransform: 'uppercase' }}>Start Date</label>
            <input type="date" style={{ width: '100%', padding: '8px', border: '1px solid var(--line)', borderRadius: '6px' }} value={startDate} onChange={(e) => setStartDate(e.target.value)} />
          </div>
          <div>
            <label style={{ font: '11px DM Mono', textTransform: 'uppercase' }}>End Date</label>
            <input type="date" style={{ width: '100%', padding: '8px', border: '1px solid var(--line)', borderRadius: '6px' }} value={endDate} onChange={(e) => setEndDate(e.target.value)} />
          </div>
          <div style={{ gridColumn: 'span 3' }}>
            <label style={{ font: '11px DM Mono', textTransform: 'uppercase' }}>Reason</label>
            <input type="text" style={{ width: '100%', padding: '8px', border: '1px solid var(--line)', borderRadius: '6px' }} value={reason} onChange={(e) => setReason(e.target.value)} />
          </div>
          <button className="btn-primary" type="submit" style={{ gridColumn: 'span 3' }}>Submit Leave Application ↗</button>
        </form>

        <h4>My Submitted Leave Applications</h4>
        <table>
          <thead>
            <tr>
              <th>Type</th>
              <th>Dates</th>
              <th>Reason</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {leaves.filter(l => l.email === user.email).map((l) => (
              <tr key={l.id}>
                <td><strong>{l.type}</strong></td>
                <td>{l.startDate} to {l.endDate}</td>
                <td>{l.reason}</td>
                <td><span className={`status-chip ${l.status === 'APPROVED' ? 'success' : 'warning'}`}>{l.status}</span></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

// 6. My Interviews (Employee Role)
function MyInterviewsModule({ user }) {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>🎙️ Scheduled Candidate / Internal Interviews</h3>
        </div>
        <div style={{ background: 'var(--bg-subtle)', padding: '18px', borderRadius: '10px', border: '1px solid var(--line)' }}>
          <h4>Senior Java Backend Developer — Technical Round 1</h4>
          <p><strong>Date & Time:</strong> Tomorrow at 10:00 AM IST</p>
          <p><strong>Interviewer:</strong> Sarah Jenkins (Engineering Manager)</p>
          <button className="btn-primary" style={{ marginTop: '10px' }}>Join Video Meeting Room 🎥</button>
        </div>
      </div>
    </div>
  )
}

// 7. My Payslips & Salary
function MyPayslipsModule({ user }) {
  const [modalOpen, setModalOpen] = useState(false)
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>💰 My Payslips & Salary Records</h3>
        </div>
        <table>
          <thead>
            <tr>
              <th>Pay Period</th>
              <th>Basic Salary</th>
              <th>HRA</th>
              <th>Bonus</th>
              <th>Net Pay</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><strong>August 2026</strong></td>
              <td>₹45,000</td>
              <td>₹18,000</td>
              <td>₹10,000</td>
              <td><strong>₹71,500</strong></td>
              <td><button className="btn-secondary" onClick={() => setModalOpen(true)}>View Payslip 📄</button></td>
            </tr>
          </tbody>
        </table>
      </div>

      {modalOpen && (
        <div className="modal-overlay" onClick={() => setModalOpen(false)}>
          <div className="modal-box" onClick={(e) => e.stopPropagation()}>
            <h3>📄 Official Payslip — August 2026</h3>
            <p><strong>Employee:</strong> {user.name} ({user.email})</p>
            <hr />
            <p>Basic Salary: ₹45,000</p>
            <p>HRA Allowance: ₹18,000</p>
            <p>Performance Bonus: ₹10,000</p>
            <p>PF Deduction: -₹3,600</p>
            <hr />
            <h4 style={{ color: 'var(--primary)' }}>Net Amount Credited: ₹71,500</h4>
            <button className="btn-primary" onClick={() => setModalOpen(false)} style={{ marginTop: '12px' }}>Close</button>
          </div>
        </div>
      )}
    </div>
  )
}

// 8. My Performance Goals
function MyPerformanceModule({ user }) {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>🎯 My Assigned Goals & Performance Review</h3>
        </div>
        <div style={{ background: 'var(--bg-subtle)', padding: '16px', borderRadius: '10px' }}>
          <h4>Goal: Build High-Throughput REST APIs</h4>
          <p><strong>Progress:</strong> 87% Complete</p>
          <div className="skill-bar-container">
            <div className="skill-bar-fill" style={{ width: '87%' }}></div>
          </div>
          <p style={{ marginTop: '12px', fontSize: '13px' }}><strong>Manager Feedback:</strong> Excellent performance and code quality on Spring Boot services.</p>
        </div>
      </div>
    </div>
  )
}

// 9. My Documents & Upload
function MyDocumentsModule({ user, documents, setDocuments, setNotice, logEvent }) {
  const [docType, setDocType] = useState('Degree Certificate')
  const [fileName, setFileName] = useState('Degree_IITD.pdf')

  const handleUpload = (e) => {
    e.preventDefault()
    const newDoc = {
      id: Date.now(),
      employeeName: user.name,
      docType: docType,
      fileName: fileName,
      institution: 'IIT Delhi',
      status: 'PENDING_VERIFICATION'
    }
    setDocuments((prev) => [newDoc, ...prev])
    setNotice(`Document (${docType}) uploaded. Submitted to HR for OCR verification.`)
    logEvent(user.name, `Uploaded document for verification: ${docType}`)
  }

  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>📂 My Uploaded Documents</h3>
        </div>

        <form onSubmit={handleUpload} style={{ display: 'flex', gap: '12px', marginBottom: '20px', background: 'var(--bg-subtle)', padding: '14px', borderRadius: '8px' }}>
          <select value={docType} onChange={(e) => setDocType(e.target.value)} style={{ padding: '8px', border: '1px solid var(--line)', borderRadius: '6px' }}>
            <option>Degree Certificate</option>
            <option>PAN Card</option>
            <option>Passport</option>
          </select>
          <input type="text" value={fileName} onChange={(e) => setFileName(e.target.value)} style={{ padding: '8px', border: '1px solid var(--line)', borderRadius: '6px', flex: 1 }} />
          <button className="btn-primary" type="submit">Upload Document ✦</button>
        </form>

        <table>
          <thead>
            <tr>
              <th>Document Type</th>
              <th>File Name</th>
              <th>Verification Status</th>
            </tr>
          </thead>
          <tbody>
            {documents.filter(d => d.employeeName === user.name).map((d) => (
              <tr key={d.id}>
                <td><strong>{d.docType}</strong></td>
                <td>{d.fileName}</td>
                <td><span className={`status-chip ${d.status === 'VERIFIED' ? 'success' : 'warning'}`}>{d.status}</span></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

// ── MANAGER MODULES ──────────────────────────────────────────
function TeamPeopleModule() {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>👥 My Direct Engineering Team</h3>
        </div>
        <table>
          <thead>
            <tr>
              <th>Employee Name</th>
              <th>Designation</th>
              <th>Goal Completion</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><strong>Rahul Sharma</strong></td>
              <td>Software Developer</td>
              <td><span className="status-chip success">87%</span></td>
            </tr>
            <tr>
              <td><strong>Vikram Malhotra</strong></td>
              <td>Senior Engineer</td>
              <td><span className="status-chip warning">60%</span></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  )
}

function TeamAttendanceModule({ attendance }) {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>⏱️ Team Attendance Log Today</h3>
        </div>
        <table>
          <thead>
            <tr>
              <th>Employee Name</th>
              <th>Date</th>
              <th>Check-In</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {attendance.map((a) => (
              <tr key={a.id}>
                <td><strong>{a.employeeName}</strong></td>
                <td>{a.date}</td>
                <td>{a.checkIn}</td>
                <td><span className={`status-chip ${a.status === 'PRESENT' ? 'success' : 'danger'}`}>{a.status}</span></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

function LeaveApprovalsModule({ leaves, setLeaves, user, setNotice, logEvent }) {
  const handleAction = (id, newStatus) => {
    setLeaves((prev) => prev.map(l => l.id === id ? { ...l, status: newStatus } : l))
    setNotice(`Leave request ${newStatus.toLowerCase()} successfully.`)
    logEvent(user.name, `${newStatus} Leave request ID ${id}`)
  }

  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>📅 Pending Team Leave Approvals</h3>
        </div>
        <table>
          <thead>
            <tr>
              <th>Employee</th>
              <th>Type</th>
              <th>Dates</th>
              <th>Reason</th>
              <th>Status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {leaves.map((l) => (
              <tr key={l.id}>
                <td><strong>{l.employeeName}</strong></td>
                <td>{l.type}</td>
                <td>{l.startDate} to {l.endDate}</td>
                <td>{l.reason}</td>
                <td><span className={`status-chip ${l.status === 'APPROVED' ? 'success' : l.status === 'REJECTED' ? 'danger' : 'warning'}`}>{l.status}</span></td>
                <td>
                  {l.status === 'PENDING' && (
                    <div style={{ display: 'flex', gap: '6px' }}>
                      <button className="btn-primary" style={{ padding: '4px 10px', fontSize: '11px' }} onClick={() => handleAction(l.id, 'APPROVED')}>Approve</button>
                      <button className="btn-secondary" style={{ padding: '4px 10px', fontSize: '11px', color: '#f5f5f0' }} onClick={() => handleAction(l.id, 'REJECTED')}>Reject</button>
                    </div>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

function TeamPerformanceModule() {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>📊 Team Performance Appraisals</h3>
        </div>
        <p style={{ fontSize: '13px' }}>All 3 direct report appraisals are active for Q3 review cycle.</p>
      </div>
    </div>
  )
}

function TeamInterviewsModule() {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>🎙️ Assigned Candidate Interviews</h3>
        </div>
        <p style={{ fontSize: '13px' }}>1 Candidate Interview scheduled for Senior Java Developer role tomorrow at 10:00 AM.</p>
      </div>
    </div>
  )
}

// ── RECRUITER MODULES ────────────────────────────────────────
function JobManagerModule({ jobs, setJobs, user, setNotice, logEvent }) {
  const [title, setTitle] = useState('')
  const [dept, setDept] = useState('Engineering')
  const [sal, setSal] = useState('₹18L - ₹24L')

  const handleCreateJob = (e) => {
    e.preventDefault()
    if (!title) return
    const newJob = {
      id: Date.now(),
      title: title,
      department: dept,
      location: 'Remote',
      experience: '3-5 years',
      salary: sal,
      status: 'OPEN',
      description: 'Newly created job requisition.'
    }
    setJobs((prev) => [newJob, ...prev])
    setTitle('')
    setNotice(`Job Requisition (${newJob.title}) published successfully!`)
    logEvent(user.name, `Published new Job Requisition: ${newJob.title}`)
  }

  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>💼 Job Requisitions & Publisher</h3>
        </div>

        <form onSubmit={handleCreateJob} style={{ display: 'flex', gap: '12px', marginBottom: '20px', background: 'var(--bg-subtle)', padding: '14px', borderRadius: '8px' }}>
          <input type="text" placeholder="Job Title (e.g. Senior Java Backend Developer)" value={title} onChange={(e) => setTitle(e.target.value)} style={{ flex: 2, padding: '8px', border: '1px solid var(--line)', borderRadius: '6px' }} />
          <input type="text" placeholder="Salary Range" value={sal} onChange={(e) => setSal(e.target.value)} style={{ flex: 1, padding: '8px', border: '1px solid var(--line)', borderRadius: '6px' }} />
          <button className="btn-primary" type="submit">Publish Job ↗</button>
        </form>

        <table>
          <thead>
            <tr>
              <th>Job Title</th>
              <th>Department</th>
              <th>Salary</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {jobs.map((j) => (
              <tr key={j.id}>
                <td><strong>{j.title}</strong></td>
                <td>{j.department}</td>
                <td>{j.salary}</td>
                <td><span className="status-chip success">{j.status}</span></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

function AtsPipelineModule({ candidates, setCandidates, logEvent }) {
  const stages = ['Applied', 'Screening', 'Shortlisted', 'Interview', 'Selected', 'Joined']

  const moveStage = (candId, nextStage) => {
    setCandidates((prev) => prev.map(c => c.id === candId ? { ...c, stage: nextStage } : c))
    logEvent('Recruiter', `Moved candidate ID ${candId} to stage: ${nextStage}`)
  }

  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>📌 ATS Recruitment Kanban Pipeline</h3>
        </div>

        <div className="kanban-board">
          {stages.map((stg) => (
            <div key={stg} className="kanban-col">
              <h4>{stg} ({candidates.filter(c => c.stage === stg).length})</h4>
              {candidates.filter(c => c.stage === stg).map((cand) => (
                <div key={cand.id} className="kanban-card">
                  <strong>{cand.name}</strong>
                  <small style={{ display: 'block' }}>{cand.jobTitle}</small>
                  <span className="status-chip success" style={{ marginTop: '4px', fontSize: '10px' }}>Score: {cand.score}%</span>
                  <div style={{ marginTop: '8px', display: 'flex', gap: '4px' }}>
                    {stages.indexOf(stg) < stages.length - 1 && (
                      <button className="btn-secondary" style={{ padding: '2px 6px', fontSize: '10px' }} onClick={() => moveStage(cand.id, stages[stages.indexOf(stg) + 1])}>
                        Advance ➔
                      </button>
                    )}
                  </div>
                </div>
              ))}
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}

function InterviewSchedulerModule({ candidates, setNotice, logEvent }) {
  const [candidateId, setCandidateId] = useState('')
  const [time, setTime] = useState('Tomorrow 10:00 AM')

  const handleSchedule = (e) => {
    e.preventDefault()
    setNotice(`Interview scheduled for Candidate successfully!`)
    logEvent('Recruiter', `Scheduled interview for Candidate ID ${candidateId} at ${time}`)
  }

  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>📅 Interview Scheduler</h3>
        </div>

        <form onSubmit={handleSchedule} style={{ display: 'flex', gap: '12px', background: 'var(--bg-subtle)', padding: '14px', borderRadius: '8px' }}>
          <select value={candidateId} onChange={(e) => setCandidateId(e.target.value)} style={{ padding: '8px', border: '1px solid var(--line)', borderRadius: '6px' }}>
            <option value="">Select Candidate...</option>
            {candidates.map(c => <option key={c.id} value={c.id}>{c.name} ({c.jobTitle})</option>)}
          </select>
          <input type="text" value={time} onChange={(e) => setTime(e.target.value)} style={{ padding: '8px', border: '1px solid var(--line)', borderRadius: '6px' }} />
          <button className="btn-primary" type="submit">Schedule Interview ✦</button>
        </form>
      </div>
    </div>
  )
}

// ── HR & ADMIN MODULES ───────────────────────────────────────
function DocumentVerificationModule({ documents, setDocuments, setNotice, logEvent }) {
  const handleVerify = (id) => {
    setDocuments((prev) => prev.map(d => d.id === id ? { ...d, status: 'VERIFIED' } : d))
    setNotice(`Document ID ${id} verified successfully by HR.`)
    logEvent('HR', `Verified document ID ${id}`)
  }

  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>📄 HR Document OCR Verification Queue</h3>
        </div>

        <table>
          <thead>
            <tr>
              <th>Employee Name</th>
              <th>Document Type</th>
              <th>File</th>
              <th>Status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {documents.map((d) => (
              <tr key={d.id}>
                <td><strong>{d.employeeName}</strong></td>
                <td>{d.docType}</td>
                <td>{d.fileName}</td>
                <td><span className={`status-chip ${d.status === 'VERIFIED' ? 'success' : 'warning'}`}>{d.status}</span></td>
                <td>
                  {d.status === 'PENDING_VERIFICATION' && (
                    <button className="btn-primary" style={{ padding: '4px 10px', fontSize: '11px' }} onClick={() => handleVerify(d.id)}>
                      Approve & Verify OCR ✦
                    </button>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

function UserManagementModule({ onSwitchUser }) {
  const demoAccounts = [
    { name: 'Rahul Sharma', email: 'rahul@nexushr.com', role: 'EMPLOYEE' },
    { name: 'Sarah Jenkins', email: 'sarah@nexushr.com', role: 'MANAGER' },
    { name: 'Alex Mercer', email: 'alex@nexushr.com', role: 'RECRUITER' },
    { name: 'Priya Nair', email: 'priya@nexushr.com', role: 'HR' },
    { name: 'Super Admin', email: 'admin@nexushr.com', role: 'ADMIN' }
  ]

  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>👥 Admin User Account Management</h3>
        </div>

        <table>
          <thead>
            <tr>
              <th>User Name</th>
              <th>Email</th>
              <th>Role</th>
              <th>Switch Context</th>
            </tr>
          </thead>
          <tbody>
            {demoAccounts.map((acc) => (
              <tr key={acc.email}>
                <td><strong>{acc.name}</strong></td>
                <td>{acc.email}</td>
                <td><span className="role-badge">{acc.role}</span></td>
                <td>
                  <button className="btn-secondary" style={{ padding: '4px 10px', fontSize: '11px' }} onClick={() => onSwitchUser(acc)}>
                    Log In As User ➔
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

function AuditLogsModule({ logs }) {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>🛡️ Enterprise Security Audit Logs</h3>
        </div>

        <table>
          <thead>
            <tr>
              <th>Timestamp</th>
              <th>Actor</th>
              <th>Security / HR Event</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {logs.map((log) => (
              <tr key={log.id}>
                <td>{log.timestamp}</td>
                <td><strong>{log.actor}</strong></td>
                <td>{log.event}</td>
                <td><span className="status-chip success">{log.status}</span></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

function SystemSecurityModule() {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header">
          <h3>🔒 Security & Failed-Login Monitoring</h3>
        </div>
        <p>System Security Status: <span className="status-chip success">NORMAL (0 active lockouts)</span></p>
      </div>
    </div>
  )
}

function ResumeScreeningModule() {
  const [result, setResult] = useState(null)
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header"><h3>📄 AI Resume Parser & Screening</h3></div>
        <button className="btn-primary" onClick={() => setResult({ name: 'Rahul Sharma', match: 88 })}>Screen Resume ✦</button>
        {result && (
          <div style={{ marginTop: '16px', padding: '14px', background: 'var(--bg-subtle)', borderRadius: '8px' }}>
            <h4>Candidate: {result.name} — Match: 88%</h4>
          </div>
        )}
      </div>
    </div>
  )
}

function CandidateRankingModule({ candidates }) {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header"><h3>🏆 AI Candidate Ranking</h3></div>
        <table>
          <thead>
            <tr><th>Candidate</th><th>Match</th></tr>
          </thead>
          <tbody>
            {candidates.map(c => <tr key={c.id}><td><strong>{c.name}</strong></td><td><span className="status-chip success">{c.score}%</span></td></tr>)}
          </tbody>
        </table>
      </div>
    </div>
  )
}

function InterviewToolsModule() {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header"><h3>🎯 AI Interview Questions & Feedback</h3></div>
        <p style={{ fontSize: '13px' }}>AI interview tools active.</p>
      </div>
    </div>
  )
}

function SkillGapModule() {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header"><h3>🚀 Skill Gap & Learning Roadmap</h3></div>
        <p style={{ fontSize: '13px' }}>Target Role: Senior Backend Developer.</p>
      </div>
    </div>
  )
}

function AttritionRiskModule() {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header"><h3>🛡️ Attrition Intelligence</h3></div>
        <p style={{ fontSize: '13px' }}>Risk Model: Low (420), Medium (75), High (25).</p>
      </div>
    </div>
  )
}

function WorkforcePlanningModule({ jobs }) {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header"><h3>📈 Workforce Planning</h3></div>
        <p style={{ fontSize: '13px' }}>Active Requisitions: {jobs.length}.</p>
      </div>
    </div>
  )
}

function LeaveIntelligenceModule({ leaves }) {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header"><h3>📊 Leave Intelligence</h3></div>
        <p style={{ fontSize: '13px' }}>Total Leaves Logged: {leaves.length}.</p>
      </div>
    </div>
  )
}

function PayrollEngineModule() {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header"><h3>💰 Payroll Engine</h3></div>
        <p style={{ fontSize: '13px' }}>August Payroll processed.</p>
      </div>
    </div>
  )
}

function PayrollAnomaliesModule() {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header"><h3>⚠️ Payroll Anomalies</h3></div>
        <p style={{ fontSize: '13px' }}>EMP1024 +23% salary spike flagged for review.</p>
      </div>
    </div>
  )
}

function EmployeeLifecycleModule() {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header"><h3>👥 Employee Lifecycle Directory</h3></div>
        <p style={{ fontSize: '13px' }}>Active Employee Accounts: 620.</p>
      </div>
    </div>
  )
}

function DepartmentsModule() {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header"><h3>🏢 Departments</h3></div>
        <p style={{ fontSize: '13px' }}>Engineering, Product, HR, Finance.</p>
      </div>
    </div>
  )
}

function HrOperationsModule({ title, eyebrow, description, metrics, actions }) {
  return (
    <div className="view-stack hr-module">
      <div className="hr-module-hero">
        <div>
          <p className="eyebrow">{eyebrow}</p>
          <h2>{title}</h2>
          <p>{description}</p>
        </div>
        <span className="hr-module-mark">HR<br />OPS</span>
      </div>

      <div className="hr-kpi-grid">
        {metrics.map((metric, index) => (
          <div className={`hr-kpi hr-kpi-${index + 1}`} key={metric}>
            <span>{String(index + 1).padStart(2, '0')}</span>
            <strong>{metric.split(' ')[0]}</strong>
            <p>{metric.split(' ').slice(1).join(' ')}</p>
          </div>
        ))}
      </div>

      <div className="panel hr-action-panel">
        <div className="panel-header">
          <div>
            <p className="eyebrow">QUICK ACTIONS</p>
            <h3>Move work forward</h3>
          </div>
          <span className="status-chip success">WORKSPACE READY</span>
        </div>
        <div className="hr-action-grid">
          {actions.map((action, index) => (
            <button className="hr-action" key={action}>
              <span>{['+', '↗', '◉'][index]}</span>
              <strong>{action}</strong>
              <small>Open workflow</small>
            </button>
          ))}
        </div>
      </div>

      <div className="panel">
        <div className="panel-header">
          <div>
            <p className="eyebrow">WORK QUEUE</p>
            <h3>Today in HR operations</h3>
          </div>
          <button className="btn-secondary">View all activity ↗</button>
        </div>
        <div className="hr-queue">
          <div><span className="queue-dot amber" /><strong>Review pending employee requests</strong><small>Due today · 8 items</small></div>
          <div><span className="queue-dot green" /><strong>Follow up with new starters</strong><small>In progress · 4 owners</small></div>
          <div><span className="queue-dot white" /><strong>Publish this month&apos;s people report</strong><small>Scheduled · Friday</small></div>
        </div>
      </div>
    </div>
  )
}

function SimpleOperationsModule({ title, eyebrow, description, metrics, actions }) {
  const [completedAction, setCompletedAction] = useState('')

  return (
    <div className="view-stack hr-module">
      <div className="hr-module-hero">
        <div>
          <p className="eyebrow">{eyebrow}</p>
          <h2>{title}</h2>
          <p>{description}</p>
        </div>
        <span className="hr-module-mark">NEXUS<br />WORKSPACE</span>
      </div>
      <div className="hr-kpi-grid">
        {metrics.map((metric, index) => (
          <div className={`hr-kpi hr-kpi-${index + 1}`} key={metric}>
            <span>{String(index + 1).padStart(2, '0')}</span>
            <strong>{metric.split(' ')[0]}</strong>
            <p>{metric.split(' ').slice(1).join(' ')}</p>
          </div>
        ))}
      </div>
      <div className="panel hr-action-panel">
        <div className="panel-header">
          <div><p className="eyebrow">WORKFLOW ACTIONS</p><h3>Choose the next step</h3></div>
          <span className="status-chip success">{completedAction ? 'ACTION LOGGED' : 'READY'}</span>
        </div>
        <div className="hr-action-grid">
          {actions.map((action, index) => (
            <button className="hr-action" key={action} onClick={() => setCompletedAction(action)}>
              <span>{['+', '↗', '◉'][index]}</span>
              <strong>{action}</strong>
              <small>{completedAction === action ? 'Completed just now' : 'Open workflow'}</small>
            </button>
          ))}
        </div>
      </div>
      <div className="panel">
        <div className="panel-header"><div><p className="eyebrow">RECENT ACTIVITY</p><h3>Latest updates</h3></div></div>
        <div className="hr-queue">
          <div><span className="queue-dot amber" /><strong>Review items requiring HR attention</strong><small>Due today</small></div>
          <div><span className="queue-dot green" /><strong>Workspace is connected to the activity log</strong><small>Live</small></div>
        </div>
      </div>
    </div>
  )
}

function PermissionsMatrixModule() {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header"><h3>🔐 Fine-Grained Permissions Matrix</h3></div>
        <p style={{ fontSize: '13px' }}>Roles: EMPLOYEE, MANAGER, RECRUITER, HR, ADMIN.</p>
      </div>
    </div>
  )
}

function AiAssistantView({ user, onAsk }) {
  return (
    <div className="view-stack">
      <div className="panel">
        <div className="panel-header"><h3>🤖 AI Assistant ({user.role} View)</h3></div>
        <button className="btn-primary" onClick={() => onAsk('What is my leave balance?')}>Ask Assistant ✦</button>
      </div>
    </div>
  )
}

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>
)