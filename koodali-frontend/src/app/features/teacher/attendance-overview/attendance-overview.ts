import { Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-attendance-overview',
  standalone : true,
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './attendance-overview.html',
  styleUrl: './attendance-overview.css',
})

//similar concept to userform - when section is chosen, display all students of section in rows.
export class AttendanceOverview implements OnInit {
  selectedSectionId: string | null = null;
  rows: any[] = [];
  columns: any[] = [];
  row: any;
  col: any;

  sections : any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadAttendances();
    this.loadSections();
  }

  loadAttendances() {
    this.http.get<any[]>(`http://localhost:8080/api/teachers/attendance`)
      .subscribe(data => {
        this.initializeTable(data);
      });
  }

  loadSections() {
    this.http.get<any[]>('http://localhost:8080/api/sections')
      .subscribe(data => {
        this.sections = data;
      });
  }

  onSectionChange(sectionId: string) {
    this.selectedSectionId = sectionId;

    this.http
      .get<any[]>(`http://localhost:8080/api/sections/${sectionId}/attendance`)
      .subscribe(data => {
        this.initializeTable(data);
      });
  }
  initializeTable(data: any[]) {
    const columnSet = new Set<string>();
    const rowMap = new Map<string, any>();

    data.forEach(entry => {
      columnSet.add(entry.week);

      if (!rowMap.has(entry.studentId)) {
        rowMap.set(entry.studentId, {
          id: rowMap.size + 1,
          studentId: entry.studentId,
          studentName: entry.studentName,
          attendance: {}
        });
      }

      rowMap.get(entry.studentId).attendance[entry.week] = entry.attended;
    });

    this.columns = Array.from(columnSet).map(w => ({
      key: w,
      label: w.toUpperCase()
    }));

    this.rows = Array.from(rowMap.values());
  }

  addRow() {
    this.rows.push({
      id: this.rows.length + 1,
      studentId: '',
      studentName: '',
      attendance: {}
    });
  }

  addColumn() {
    const nextDate = this.getNextWeekDate(); // class schedule
    const key = nextDate.toISOString().slice(0, 10);

    this.columns.push({
      key,
      label: key,
      date: nextDate
    });

    this.rows.forEach(r => r.attendance[key] = false);
  }

  getNextWeekDate(): Date {
    if (this.columns.length === 0) {
      return new Date();
    }

    const last = new Date(this.columns[this.columns.length - 1].key);
    last.setDate(last.getDate() + 7);
    return last;
  }


  toggleAttendance(row: any, columnKey: string) {
    row.attendance[columnKey] = !row.attendance[columnKey];

    this.http.patch(
      `http://localhost:8080/api/teachers/attendance`,
      {
        studentId: row.studentId,
        week: columnKey,
        attended: row.attendance[columnKey]
      }
    ).subscribe();
  }

}
