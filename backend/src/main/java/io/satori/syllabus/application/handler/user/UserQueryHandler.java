package io.satori.syllabus.application.handler.user;

import org.springframework.data.domain.Page;
import io.satori.syllabus.application.dto.TokenDTO;
import io.satori.syllabus.application.query.user.GetActiveDirectorsQuery;
import io.satori.syllabus.application.query.user.GetActiveOfficesQuery;
import io.satori.syllabus.application.query.user.GetActiveStudentsQuery;
import io.satori.syllabus.application.query.user.GetActiveTeachersQuery;
import io.satori.syllabus.application.query.user.GetActiveUsersQuery;
import io.satori.syllabus.application.query.user.GetArchivedDirectorsQuery;
import io.satori.syllabus.application.query.user.GetArchivedOfficesQuery;
import io.satori.syllabus.application.query.user.GetArchivedStudentsQuery;
import io.satori.syllabus.application.query.user.GetArchivedTeachersQuery;
import io.satori.syllabus.application.query.user.GetArchivedUsersQuery;
import io.satori.syllabus.application.query.user.GetDirectorTokensQuery;
import io.satori.syllabus.application.query.user.GetLoggedInUserQuery;
import io.satori.syllabus.application.query.user.GetNotSupervisingActiveTeachersQuery;
import io.satori.syllabus.application.query.user.GetOfficeTokensQuery;
import io.satori.syllabus.application.query.user.GetStudentTokensQuery;
import io.satori.syllabus.application.query.user.GetTeacherTokensQuery;
import io.satori.syllabus.application.query.user.GetUnassignedStudentsQuery;
import io.satori.syllabus.application.query.user.GetUserByIdQuery;
import io.satori.syllabus.application.query.user.GetUserByKeywordQuery;
import io.satori.syllabus.domain.model.User;

import java.util.List;

public interface UserQueryHandler {

    User handle(GetUserByIdQuery query);

    Page<User> handle(GetActiveUsersQuery getActiveUsersQuery);

    Page<User> handle(GetArchivedUsersQuery getArchivedUsersQuery);

    Page<User> handle(GetUserByKeywordQuery query);

    Page<User> handle(GetActiveStudentsQuery query);

    Page<User> handle(GetActiveTeachersQuery query);

    Page<User> handle(GetActiveOfficesQuery query);

    Page<User> handle(GetActiveDirectorsQuery query);

    Page<User> handle(GetArchivedStudentsQuery query);

    Page<User> handle(GetArchivedTeachersQuery query);

    Page<User> handle(GetArchivedOfficesQuery query);

    Page<User> handle(GetArchivedDirectorsQuery query);

    User handle(GetLoggedInUserQuery query);

    List<User> handle(GetNotSupervisingActiveTeachersQuery query);

    Page<TokenDTO> handle(GetStudentTokensQuery query);

    Page<TokenDTO> handle(GetTeacherTokensQuery query);

    Page<TokenDTO> handle(GetOfficeTokensQuery query);

    Page<TokenDTO> handle(GetDirectorTokensQuery query);

    Page<User> handle(GetUnassignedStudentsQuery query);
}
