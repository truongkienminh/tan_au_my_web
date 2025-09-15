# --- Build stage: dùng image Maven (có JDK21) để build jar ---
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /workspace

# copy pom trước để tận dụng cache layer for dependencies
COPY pom.xml ./

# tải dependencies để cache, tránh tải lại nhiều lần
RUN mvn -B dependency:go-offline

# copy source và build jar
COPY src ./src
RUN mvn -B -DskipTests package

# --- Run stage: nhỏ gọn, chỉ JRE21 ---
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# copy jar đã build từ stage trước
COPY --from=build /workspace/target/*.jar app.jar

# chỉ định port (Render sẽ inject biến PORT)
EXPOSE 8080

# dùng shell form để expand biến môi trường PORT
CMD ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar /app/app.jar"]
